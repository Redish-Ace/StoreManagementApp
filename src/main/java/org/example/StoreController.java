package org.example;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class StoreController {
    StoreView view = new StoreView();
    StoreModel model = new StoreModel();
    ExtractCustomization custom = new ExtractCustomization();
    StoreDatabase database = new StoreDatabase();
    SetCustom set = new SetCustom();

    public StoreController(){
        set.setSettings(view, custom);
        model.getProducts(view.mdlSelect, database);
        addAction();
        StoreView.createAndShowGUI(view);
    }

    public void addAction() {
        view.btnAdd.addActionListener(e -> {
             model.addProducts(view);
        });

        view.btnRemove.addActionListener(e -> {
            model.removeProducts(view);
        });

        view.btnBuy.addActionListener(e -> {
            model.buyProducts(view, database);
        });

        view.boxType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model.filterProducts(view, view.mdlSelect, database);
            }
        });

        view.itemBuy.addActionListener(e -> {
            if(view.pnlBuy.getComponentCount() > 0){
                view.pnlBuy.removeAll();
                view.pnlCart.removeAll();
                view.pnlCartList.removeAll();
                view.pnlSelectProduct.removeAll();
                view.pnlSPTop.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);
            model.getProducts(view.mdlSelect, database);

            view.frame.getContentPane().removeAll();

            view.frame.add(view.customer(), BorderLayout.CENTER);
            view.pnlBuy.revalidate();
            view.pnlBuy.repaint();

            view.frame.revalidate();
            view.frame.repaint();

        });
        view.itemLogin.addActionListener(e -> {
            if(view.pnlLogin.getComponentCount() > 0){
                view.pnlLogin.removeAll();
            }
            if(view.pnlText.getComponentCount() > 0){
                view.pnlText.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);

            view.frame.getContentPane().removeAll();

            view.frame.add(view.login(), BorderLayout.CENTER);
            view.pnlLogin.revalidate();
            view.pnlLogin.repaint();

            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnLogin.addActionListener(e -> {
            model.login(view, database);
        });

        view.itemLogOut.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Log Out", JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.NO_OPTION) return;
            if(view.pnlLogin.getComponentCount() > 0){
                view.pnlLogin.removeAll();
            }
            if(view.pnlText.getComponentCount() > 0){
                view.pnlText.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);

            view.frame.getContentPane().removeAll();
            view.frame.setJMenuBar(view.customerMenu());

            view.frame.add(view.login(), BorderLayout.CENTER);
            view.pnlLogin.revalidate();
            view.pnlLogin.repaint();

            view.frame.revalidate();
            view.frame.repaint();
        });
        view.itemNewStore.addActionListener(e -> {
            if (view.pnlNewStore.getComponentCount() > 0) {
                view.pnlNewStore.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.newStore(), BorderLayout.CENTER);
            view.pnlNewStore.revalidate();
            view.pnlNewStore.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnStoreSave.addActionListener(e -> {
            if(!model.canSaveStore(view)) return;
            model.insertStore(view, database);
        });

        view.itemListStore.addActionListener(e -> {
            if (view.pnlListStore.getComponentCount() > 0) {
                view.pnlListStore.removeAll();
            }
            model.getStores(view.mdlStore, database);
            set.resetText(view);
            set.removePanelComponents(view);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.listStore(), BorderLayout.CENTER);
            view.pnlListStore.revalidate();
            view.pnlListStore.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnStoreDel.addActionListener(e -> model.deleteStore(view, database));

        view.itemNewProduct.addActionListener(e -> {
            if (view.pnlNewProduct.getComponentCount() > 0) {
                view.pnlNewProduct.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);
            model.getStoreBox(view);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.newProduct(), BorderLayout.CENTER);
            view.pnlNewProduct.revalidate();
            view.pnlNewProduct.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnProductSave.addActionListener(e -> {
            if(!model.canSaveProduct(view)) return;
            model.insertProducts(view, database);
        });

        view.itemListProduct.addActionListener(e -> {
            if (view.pnlListProduct.getComponentCount() > 0) {
                view.pnlListProduct.removeAll();
            }
            model.getProducts(view.mdlProduct, database);
            set.resetText(view);
            set.removePanelComponents(view);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.listProduct(), BorderLayout.CENTER);
            view.pnlListProduct.revalidate();
            view.pnlListProduct.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnAll.addActionListener(e -> model.getProducts(view.mdlProduct, database));
        view.btnMax.addActionListener(e -> model.selectMax(view, database));
        view.btnByStore.addActionListener(e -> model.selectByStore(view, database));
        view.btnShowSale.addActionListener(e -> model.selectOnSale(view));
        view.btnMostSold.addActionListener(e -> model.selectMostSold(view, database));
        view.btnProductDel.addActionListener(e -> model.deleteProducts(view, database));

        view.itemSale.addActionListener(e -> {
            if (view.pnlSale.getComponentCount() > 0) {
                view.pnlSale.removeAll();
            }
            if (view.pnlText.getComponentCount() > 0) {
                view.pnlText.removeAll();
            }
            if(view.pnlProductInfo.getComponentCount() > 0){
                view.pnlProductInfo.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);
            model.clearProductTable(view);
            model.productBox(view);
            model.filterSale(view);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.saleProduct(), BorderLayout.CENTER);
            view.pnlSale.revalidate();
            view.pnlSale.repaint();
            view.pnlNorth.revalidate();
            view.pnlNorth.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.boxProduct.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model.filterSale(view);
            }
        });

        view.txtPercent.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.getNewPrice(view, database);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.getNewPrice(view, database);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                model.getNewPrice(view, database);
            }
        });

        view.btnSale.addActionListener(e -> model.insertSale(view, database));

        view.itemSetFont.addActionListener(e -> {
            if (view.pnlFontSettings.getComponentCount() > 0) {
                view.pnlFontSettings.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);
            view.frame.getContentPane().removeAll();

            set.setCurrentFont(view, custom);

            view.frame.add(view.setFont(), BorderLayout.CENTER);
            view.pnlFontSettings.revalidate();
            view.pnlFontSettings.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });
        view.itemSetColor.addActionListener(e -> {
            if (view.pnlColorSettings.getComponentCount() > 0) {
                view.pnlColorSettings.removeAll();
            }
            set.resetText(view);
            set.removePanelComponents(view);
            set.setCurrentColor(view, custom);

            view.frame.getContentPane().removeAll();
            view.frame.add(view.setColor(), BorderLayout.CENTER);
            view.pnlColorSettings.revalidate();
            view.pnlColorSettings.repaint();
            view.frame.revalidate();
            view.frame.repaint();
        });

        view.btnFontSave.addActionListener(e -> {
            set.saveFonts(view, custom);
            set.setSettings(view, custom);
            set.setCurrentFont(view, custom);
            view.pnlFontSettings.revalidate();
            view.pnlFontSettings.repaint();
        });
        view.btnFontClear.addActionListener(e -> set.setCurrentFont(view, custom));
        view.btnFontReset.addActionListener(e -> set.resetFont(view));

        view.btnBackgroundColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtBackgroundColor.setForeground(new Color(set.textColor(rgb)));
                view.txtBackgroundColor.setText(rgb + "");
                view.txtBackgroundColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTitleColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTitleColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTitleColor.setText(rgb+"");
                view.txtTitleColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnLabelColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtLabelColor.setForeground(new Color(set.textColor(rgb)));
                view.txtLabelColor.setText(rgb+"");
                view.txtLabelColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTextColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTextColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTextColor.setText(rgb+"");
                view.txtTextColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTextBackColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTextBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTextBackColor.setText(rgb+"");
                view.txtTextBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnBtnColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtBtnColor.setForeground(new Color(set.textColor(rgb)));
                view.txtBtnColor.setText(rgb+"");
                view.txtBtnColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnBtnBackColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtBtnBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtBtnBackColor.setText(rgb+"");
                view.txtBtnBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnMenuColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtMenuColor.setForeground(new Color(set.textColor(rgb)));
                view.txtMenuColor.setText(rgb+"");
                view.txtMenuColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnMenuBackColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtMenuBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtMenuBackColor.setText(rgb+"");
                view.txtMenuBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnMenuItemColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtMenuItemColor.setForeground(new Color(set.textColor(rgb)));
                view.txtMenuItemColor.setText(rgb+"");
                view.txtMenuItemColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnMenuItemBackColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtMenuItemBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtMenuItemBackColor.setText(rgb+"");
                view.txtMenuItemBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTableColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTableColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTableColor.setText(rgb+"");
                view.txtTableColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTableBackColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTableBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTableBackColor.setText(rgb+"");
                view.txtTableBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTableHeadColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTableHeadColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTableHeadColor.setText(rgb+"");
                view.txtTableHeadColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnTableHeadColor.addActionListener(e -> {
            try {
                int rgb = set.selectColor(view);
                view.txtTableHeadBackColor.setForeground(new Color(set.textColor(rgb)));
                view.txtTableHeadBackColor.setText(rgb+"");
                view.txtTableHeadBackColor.setBackground(new Color(rgb));
            }catch(Exception ex){}
        });
        view.btnColorSave.addActionListener(e -> {
            set.saveColors(view, custom);
            set.setSettings(view, custom);
            set.setCurrentColor(view, custom);
            view.pnlColorSettings.revalidate();
            view.pnlColorSettings.repaint();
        });
        view.btnColorClear.addActionListener(e -> {
            set.setCurrentColor(view, custom);
            allTextColor();
        });
        view.btnColorReset.addActionListener(e ->{
                set.resetColor(view);
                allTextColor();
        });
    }

    private void allTextColor(){
        view.txtBackgroundColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtBackgroundColor.getText()))));
        view.txtTitleColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTitleColor.getText()))));
        view.txtLabelColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtLabelColor.getText()))));
        view.txtTextColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTextColor.getText()))));
        view.txtTextBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTextBackColor.getText()))));
        view.txtBtnColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtBtnColor.getText()))));
        view.txtBtnBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtBtnBackColor.getText()))));
        view.txtMenuColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtMenuColor.getText()))));
        view.txtMenuBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtMenuBackColor.getText()))));
        view.txtMenuItemColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtMenuItemColor.getText()))));
        view.txtMenuItemBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtMenuItemBackColor.getText()))));
        view.txtTableColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTableColor.getText()))));
        view.txtTableBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTableBackColor.getText()))));
        view.txtTableHeadColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTableHeadColor.getText()))));
        view.txtTableHeadBackColor.setForeground(new Color(set.textColor(Integer.parseInt(view.txtTableHeadBackColor.getText()))));
    }
}
