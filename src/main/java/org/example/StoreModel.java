package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class StoreModel {
    private ArrayList<Products> arrProducts = new ArrayList<>();
    private ArrayList<Products> arrSold = new ArrayList<>();
    private ArrayList<Stores> arrStore = new ArrayList<>();

    public void getProducts(DefaultTableModel model, StoreDatabase database){
        if(model.getRowCount() > 0){
            for(int index = model.getRowCount()-1; index >= 0; index--){
                model.removeRow(index);
            }
        }

        if(arrStore.isEmpty()){
            newStoreArray(database);
        }

        if(arrProducts.isEmpty()){
            newProductsArray(database);
        }

        Object[] product = new Object[7];
        for(Products item : arrProducts) {
            product[0] = item.getId();
            product[1] = item.getName();
            product[2] = item.getType();
            product[3] = item.getQuantity();
            product[4] = item.getPrice();
            product[5] = item.getSale();
            product[6] = item.getStore();

            model.addRow(product);
        }
    }

    private void newProductsArray(StoreDatabase database){
        try {
            Statement statement = database.connect().createStatement();

            String query = "Select ProductID, Product_Name, Product_Type, Quantity, Price, Sale_percent, Subsidiary_Name from productsView Order By ProductID ASC";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int quantity = resultSet.getInt(4);
                String name = resultSet.getString(2), type = resultSet.getString(3), store = resultSet.getString(7);
                double price = resultSet.getDouble(5);
                double sale = 0;
                if(resultSet.getString(6) != null){
                    sale = resultSet.getInt(6);
                }
                arrProducts.add(new Products(id, name, type, quantity, price, sale, store));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProducts(StoreView view){
            if (view.tblSelect.getSelectedRow() < 0) return;

            int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "How many do you need?"));
            int row = view.tblSelect.getSelectedRow();
            int newQuant = Integer.parseInt(view.mdlSelect.getValueAt(row, 3).toString()) - quantity;

            if (newQuant < 0) {
                JOptionPane.showMessageDialog(null, "You're trying to take too much!");
                return;
            }

            String productName = view.mdlSelect.getValueAt(row, 1).toString();
            boolean productExists = false;

            for (int i = 0; i < view.mdlCart.getRowCount(); i++) {
                if (view.mdlCart.getValueAt(i, 1).toString().equals(productName)) {
                    int existingQuantity = Integer.parseInt(view.mdlCart.getValueAt(i, 3).toString());
                    int updatedQuantity = existingQuantity + quantity;
                    view.mdlCart.setValueAt(updatedQuantity, i, 3);
                    double pricePerUnit = Double.parseDouble(view.mdlCart.getValueAt(i, 4).toString()) / existingQuantity;
                    view.mdlCart.setValueAt(updatedQuantity * pricePerUnit, i, 4);
                    productExists = true;
                    break;
                }
            }

            if (!productExists) {
                Object[] product = new Object[7];
                for (int index = 0; index < 6; index++) {
                    product[index] = view.mdlSelect.getValueAt(row, index);
                }
                product[3] = quantity;
                product[4] = quantity * Double.parseDouble(product[4].toString());
                product[6] = view.mdlSelect.getValueAt(row, 6);
                view.mdlCart.addRow(product);
            }

            view.mdlSelect.setValueAt(newQuant, row, 3);
            arrProducts.get(row).setQuantity(newQuant);

            manageSort(arrProducts.get(row).getName(), quantity, row, true);
        }

    public void removeProducts(StoreView view){
        if(view.tblCart.getSelectedRow() < 0) return;

        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;

        int row = view.tblCart.getSelectedRow();
        int selectRow = 0;
        for(int index = 0; index < view.mdlSelect.getRowCount(); index++){
            if(view.mdlSelect.getValueAt(index, 0).toString().equals(view.mdlCart.getValueAt(row, 0).toString())){
                selectRow = index;
                break;
            }
        }

        int newQuant = Integer.parseInt(view.mdlSelect.getValueAt(selectRow, 3).toString()) + Integer.parseInt(view.mdlCart.getValueAt(row, 3).toString());
        view.mdlSelect.setValueAt(newQuant, selectRow, 3);
        arrProducts.get(row).setQuantity(newQuant);

        manageSort(view.mdlCart.getValueAt(row, 1).toString(), Integer.parseInt(view.mdlCart.getValueAt(row, 3).toString()),  row, false);
        view.mdlCart.removeRow(row);
    }

    public void manageSort(String name, int quantity, int index, boolean add){
        if(checkArray(name, arrSold)){
            int indexSold = arrayIndex(name, arrSold);
            if(add) {
                arrSold.get(indexSold).setQuantity(arrSold.get(indexSold).getQuantity() + quantity);
                arrSold.get(indexSold).setPrice(arrProducts.get(indexSold).getPrice() * arrSold.get(indexSold).getQuantity());
            }
            else{
                arrSold.remove(indexSold);
            }
        }
        else{
            arrSold.add(new Products(arrProducts.get(index).getId(), arrProducts.get(index).getName(), arrProducts.get(index).getType(), quantity, arrProducts.get(index).getPrice() * quantity, arrProducts.get(index).getStore()));
        }
    }

    private boolean checkArray(String name, ArrayList<Products> arr){
        for(Products item : arr){
            if(item.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private int arrayIndex(String name, ArrayList<Products> arr){
        for(int index = 0; index < arr.size(); index++){
            if(arr.get(index).getName().equals(name)){
                return index;
            }
        }
        return -1;
    }

    public void buyProducts(StoreView view, StoreDatabase database){
        if(view.mdlCart.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Add items to cart");
            return;
        }

        double total = 0;
        for(Products item : arrSold) {
            total += item.getPrice();
        }
        int answer = JOptionPane.showConfirmDialog(null, "Your Total is "+total+" lei\nAre you ready to buy?", "Buy items", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;

        try{
            getSoldArray(database);

            String query = "Exec insertSold ?, ?, ?, ?, ?";

            for(int index = 0; index < view.tblCart.getRowCount(); index++) {
                PreparedStatement prepStatement = database.connect().prepareStatement(query);
                prepStatement.setInt(1, Integer.parseInt(view.tblCart.getValueAt(index, 0).toString()));
                prepStatement.setString(2, view.tblCart.getValueAt(index, 1).toString());
                prepStatement.setString(3, view.tblCart.getValueAt(index, 2).toString());
                prepStatement.setInt(4, Integer.parseInt(view.tblCart.getValueAt(index, 3).toString()));
                prepStatement.setDouble(5, Double.parseDouble(view.tblCart.getValueAt(index, 4).toString()));
                prepStatement.execute();
            }

            query = "Exec updateQuantity ?, ?";


            for(Products item : arrProducts) {
                PreparedStatement prepStatement = database.connect().prepareStatement(query);
                prepStatement.setInt(1, item.getId());
                prepStatement.setInt(2, item.getQuantity());
                prepStatement.execute();
            }

            for(int index = view.tblCart.getRowCount()-1; index >= 0 ; index--){
                view.mdlCart.removeRow(index);
            }

            for(int index = view.tblSelect.getRowCount()-1; index >= 0 ; index--){
                view.mdlSelect.removeRow(index);
            }

            getProducts(view.mdlSelect, database);

            JOptionPane.showMessageDialog(null, "Thanks for buying from us!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void getSoldArray(StoreDatabase database){
        try {
            Statement statement = database.connect().createStatement();

            String query = "Select * from Sold_Products";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString(1));
                int quantity = Integer.parseInt(resultSet.getString(4));
                String name = resultSet.getString(2), type = resultSet.getString(3);
                double price = Double.parseDouble(resultSet.getString(5));

                if(checkArray(name, arrSold)){
                    int index = arrayIndex(name, arrSold);
                    arrSold.get(index).setQuantity(arrSold.get(index).getQuantity() + quantity);
                    arrSold.get(index).setPrice(arrSold.get(index).getPrice() + price);
                }
                else{
                    arrSold.add(new Products(id, name, type, quantity, price, ""));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void filterProducts(StoreView view, DefaultTableModel model, StoreDatabase database){
        for(int index = view.tblSelect.getRowCount()-1; index >= 0 ; index--){
            model.removeRow(index);
        }

        Object[] product = new Object[7];
        if(view.boxType.getSelectedIndex() == 0){
            getProducts(model, database);
            return;
        }

        for(Products item : arrProducts) {
            if(item.getType().equals(view.boxType.getSelectedItem().toString())) {
                product[0] = item.getId();
                product[1] = item.getName();
                product[2] = item.getType();
                product[3] = item.getQuantity();
                product[4] = item.getPrice();
                product[5] = item.getSale();
                product[6] = item.getStore();

                model.addRow(product);
            }
        }
    }

    public void login(StoreView view, StoreDatabase database){
        String[] user = new String[100], pass = new String[100];
        int total = 0;
        try{
            Statement statement = database.connect().createStatement();
            String query = "Select Username, Account_Password From Accounts";
            ResultSet set = statement.executeQuery(query);

            while(set.next()){
                user[total] = set.getString(1);
                pass[total] = set.getString(2);
                total++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int index = 0; index < total; index ++) {
            if (view.txtUser.getText().equals(user[index]) && view.txtPass.getText().toString().equals(pass[index])) {
                view.frame.remove(view.pnlLogin);
                view.frame.setJMenuBar(view.createMenu());
                view.frame.add(view.pnlMain);
                view.frame.revalidate();
                view.frame.repaint();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Username or Password incorrect");
    }

    public void getStores(DefaultTableModel model, StoreDatabase database){
        if(model.getRowCount() > 0){
            for(int index = model.getRowCount()-1; index >= 0; index--){
                model.removeRow(index);
            }
        }

        if(arrStore.isEmpty()){
            newStoreArray(database);
        }

        Object[] product = new Object[4];
        for(Stores item : arrStore) {
            product[0] = item.getId();
            product[1] = item.getName();
            product[2] = item.getStreet();
            product[3] = item.getPhone();

            model.addRow(product);
        }
    }

    public void newStoreArray(StoreDatabase database){
        try {
            Statement statement = database.connect().createStatement();

            String query = "Select * from Subsidiary";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString(1));
                String name = resultSet.getString(2), street = resultSet.getString(3);
                long phone = Long.parseLong(resultSet.getString(4));

                arrStore.add(new Stores(id, name, street, phone));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStore(StoreView view, StoreDatabase database){
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to save this store?", "Save", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;
        try {
            if(!checkText(view.txtStoreID.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Numbers as ID!");
                return;
            }
            if(!checkText(view.txtStorePhone.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Numbers as Phone Number!");
                return;
            }

            int id = Integer.parseInt(view.txtStoreID.getText());
            String name = view.txtStoreName.getText(), street = view.txtStoreAddress.getText();
            long phone = Long.parseLong(view.txtStorePhone.getText());

            String query = "Insert Into Subsidiary Values (?, ?, ?, ?)";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, id);
            prepStatement.setString(2, name);
            prepStatement.setString(3, street);
            prepStatement.setLong(4, phone);
            prepStatement.execute();

            view.txtStoreID.setText("");
            view.txtStoreName.setText("");
            view.txtStoreAddress.setText("");
            view.txtStorePhone.setText("");

            arrStore.add(new Stores(id, name, street, phone));

            JOptionPane.showMessageDialog(null, "Store info successfully saved");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean canSaveStore(StoreView view){
        if(view.txtStoreID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Store ID");
            return false;
        }
        if(view.txtStoreName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Store Name");
            return false;
        }
        if(view.txtStoreAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Store Address");
            return false;
        }
        if(view.txtStorePhone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Store Phone Number");
            return false;
        }
        return true;
    }

    public void deleteStore(StoreView view, StoreDatabase database){
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure", "Delete Store", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;
        try{
            int row = view.tblStore.getSelectedRow();
            int id = Integer.parseInt(view.tblStore.getValueAt(row, 0).toString());

            String query = "Delete from Subsidiary Where ID = (?)";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, id);
            prepStatement.execute();

            arrStore.remove(row);

            for(int index = arrProducts.size() - 1; index > 0; index--){
                arrProducts.remove(index);
            }

            newProductsArray(database);

            view.mdlStore.removeRow(row);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void getStoreBox(StoreView view){
        for(Stores store : arrStore){
            if(view.mdlBoxStore.getIndexOf(store.getName()) == -1) {
                view.mdlBoxStore.addElement(store.getName());
            }
        }
    }

    public void insertProducts(StoreView view, StoreDatabase database){
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to save this product?", "Save", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;
        try {
            if(!checkText(view.txtProductID.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Number as Product ID!");
                return;
            }
            if(!checkText(view.txtProductQuantity.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Number as Product Quantity!");
                return;
            }
            if(!checkText(view.txtProductPrice.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Number as Product Price!");
                return;
            }

            int idProduct = Integer.parseInt(view.txtProductID.getText()),
                    quantity = Integer.parseInt(view.txtProductQuantity.getText()),
                    idStore = 0, idProdStore = 0, idSale = 0;
            String name = view.txtProductName.getText(), type = view.boxProductType.getSelectedItem().toString(),
                    store = view.boxStore.getSelectedItem().toString();
            double price = Double.parseDouble(view.txtProductPrice.getText());

            for(Stores item : arrStore){
                if(item.getName().equals(store)){
                    idStore = item.getId();
                    break;
                }
            }

            String query = "Select Count(ID) From Products_and_Subsidiary";
            Statement statement = database.connect().createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                idProdStore = set.getInt(1) + 1;
            }

            query = "Select Count(ID) From Sale";
            statement = database.connect().createStatement();
            set = statement.executeQuery(query);
            while (set.next()) {
                idSale = set.getInt(1) + 1;
            }

            query = "Exec insertProducts ?, ?, ?, ?, ?, ?, ?, ?, ?";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, idProduct);
            prepStatement.setString(2, name);
            prepStatement.setString(3, type);
            prepStatement.setInt(4, quantity);
            prepStatement.setDouble(5, price);
            prepStatement.setInt(6, idStore);
            prepStatement.setInt(7, idProdStore);
            prepStatement.setInt(8, idSale);
            prepStatement.setDouble(9, 0.0);
            prepStatement.execute();

            view.txtProductID.setText("");
            view.txtProductQuantity.setText("");
            view.txtProductPrice.setText("");
            view.txtProductName.setText("");
            view.boxProductType.setSelectedIndex(0);
            view.boxStore.setSelectedIndex(0);

            arrProducts.add(new Products(idProduct, name, type, quantity, price, 1, store));

            JOptionPane.showMessageDialog(null, "Product info successfully saved");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean canSaveProduct(StoreView view){
        if(view.txtProductID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Product ID");
            return false;
        }
        if(view.txtProductName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Product Name");
            return false;
        }
        if(view.boxProductType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Select Prodcut Type");
            return false;
        }
        if(view.txtProductQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Product Quantity");
            return false;
        }
        if(view.txtProductPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Product Price");
            return false;
        }
        return true;
    }

    public void selectMax(StoreView view, StoreDatabase database){
        String store = JOptionPane.showInputDialog(null, "Name the store?");
        int id = 0;

        for(Stores sub : arrStore){
            if(sub.getName().equals(store)){
                id = sub.getId();
                break;
            }
        }

        clearProductTable(view);

        try{
            String query = "Select * from maxProducts(?)";

            PreparedStatement statement = database.connect().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Object product[] = new Object[7];
            while (resultSet.next()) {
                product[0] = resultSet.getString(1);
                product[1] = resultSet.getString(2);
                product[2] = resultSet.getString(3);
                product[3] = resultSet.getString(4);
                product[4] = resultSet.getString(5);
                product[5] = resultSet.getString(6);
                product[6] = resultSet.getString(7);

                view.mdlProduct.addRow(product);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void selectByStore(StoreView view, StoreDatabase database){
        String store = JOptionPane.showInputDialog(null, "Name the store?");
        int id = 0;

        for(Stores sub : arrStore){
            if(sub.getName().equals(store)){
                id = sub.getId();
                break;
            }
        }

        clearProductTable(view);

        try{
            String query = "Select * from storeProducts(?)";

            PreparedStatement statement = database.connect().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Object product[] = new Object[7];
            while (resultSet.next()) {
                product[0] = resultSet.getString(1);
                product[1] = resultSet.getString(2);
                product[2] = resultSet.getString(3);
                product[3] = resultSet.getString(4);
                product[4] = resultSet.getString(5);
                product[5] = resultSet.getString(6);
                product[6] = resultSet.getString(7);

                view.mdlProduct.addRow(product);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void selectOnSale(StoreView view){
        clearProductTable(view);

        Object[] product = new Object[7];
        for(Products item : arrProducts){
            if(item.getSale() > 0){
                product[0] = item.getId();
                product[1] = item.getName();
                product[2] = item.getType();
                product[3] = item.getQuantity();
                product[4] = item.getPrice();
                product[5] = item.getSale();
                product[6] = item.getStore();

                view.mdlProduct.addRow(product);
            }
        }
    }

    public void selectMostSold(StoreView view, StoreDatabase database){
        clearProductTable(view);

        try{
            Statement statement = database.connect().createStatement();
            String query = "Select * from soldView";
            ResultSet resultSet = statement.executeQuery(query);
            Object product[] = new Object[7];
            while (resultSet.next()) {
                product[0] = resultSet.getString(1);
                product[1] = resultSet.getString(2);
                product[2] = resultSet.getString(3);
                product[3] = resultSet.getString(4);
                product[4] = resultSet.getString(5);
                product[5] = resultSet.getString(6);
                product[6] = resultSet.getString(7);

                view.mdlProduct.addRow(product);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProducts(StoreView view, StoreDatabase database){
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure", "Delete Product}", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.NO_OPTION) return;
        try{
            int row = view.tblProduct.getSelectedRow();
            int id = Integer.parseInt(view.tblProduct.getValueAt(row, 0).toString());

            String query = "Delete from Products Where ID = (?)";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, id);
            prepStatement.execute();

            view.mdlProduct.removeRow(row);
            arrProducts.remove(row);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void productBox(StoreView view){
        for(Products item : arrProducts){
            if(view.mdlBoxProduct.getIndexOf(item.getId()) == -1){
                view.mdlBoxProduct.addElement(item.getId());
            }
        }
    }

    public void filterSale(StoreView view){
        clearProductTable(view);

        Object[] product = new Object[7];
        for(Products item : arrProducts){
            if(item.getId() == Integer.parseInt(view.boxProduct.getSelectedItem().toString())){
                product[0] = item.getId();
                product[1] = item.getName();
                product[2] = item.getType();
                product[3] = item.getQuantity();
                product[4] = item.getPrice();
                product[5] = item.getSale();
                product[6] = item.getStore();

                view.mdlProduct.addRow(product);
                break;
            }
        }
    }

    public void clearProductTable(StoreView view){
        if(view.mdlProduct.getRowCount() > 0){
            for(int index = view.mdlProduct.getRowCount()-1; index >= 0; index--){
                view.mdlProduct.removeRow(index);
            }
        }
    }

    public void getNewPrice(StoreView view, StoreDatabase database){
        try{
            int idProduct = Integer.parseInt(view.boxProduct.getSelectedItem().toString());
            double percent = 0;

            if(!view.txtPercent.getText().isEmpty()) {
                percent = Double.parseDouble(view.txtPercent.getText());
            }

            if(percent > 100 || percent < 0){
                JOptionPane.showMessageDialog(null, "Input Only Between 0 and 100!");
                return;
            }

            double originalPrice = 0;

            String query = "Select Price From Products Where ID = ?";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, idProduct);
            ResultSet set = prepStatement.executeQuery();

            while(set.next()){
                originalPrice = set.getDouble(1);
            }


            view.lblChangedPrice.setText(originalPrice - originalPrice * percent / 100 + "");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertSale(StoreView view, StoreDatabase database){
        try{
            if(view.txtPercent.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter Percentage!");
                return;
            }

            if(!checkText(view.txtPercent.getText())){
                JOptionPane.showMessageDialog(null, "Enter Only Number as Sale!");
                return;
            }

            int idProduct = Integer.parseInt(view.boxProduct.getSelectedItem().toString());
            double percent = 0;

            if(!view.txtPercent.getText().isEmpty()) {
                percent = Double.parseDouble(view.txtPercent.getText());
            }

            if(percent > 100 || percent < 0){
                JOptionPane.showMessageDialog(null, "Input Only Between 0 and 100!");
                return;
            }

            double originalPrice = 0;

            String query = "Select Price From Products Where ID = ?";
            PreparedStatement prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, idProduct);
            ResultSet set = prepStatement.executeQuery();

            while(set.next()){
                originalPrice = set.getDouble(1);
            }

            query = "Exec updateSale ?, ?, ?";
            prepStatement = database.connect().prepareStatement(query);
            prepStatement.setInt(1, idProduct);
            prepStatement.setDouble(2, originalPrice);
            prepStatement.setDouble(3, percent);
            prepStatement.execute();

            arrProducts.get(idProduct-1).setSale(percent);
            arrProducts.get(idProduct-1).setPrice(originalPrice - originalPrice * percent / 100);

            filterSale(view);
            JOptionPane.showMessageDialog(null, "Sale successfully saved!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private boolean checkText(String text){
        if(text.isEmpty()) return false;
        int count = 0;
        int length = text.length();
        for(char character : text.toCharArray()){
            if((character >= '0' && character <= '9') || character == '-' || character == '.'){
                count ++;
            }
        }
        return count == length;
    }
}