package org.example;

import javax.swing.*;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StoreView {
    public int frameWidth = 550, frameHeight = 800;
    public JFrame frame = new JFrame("Store");

    public static void createAndShowGUI(StoreView app){
        JFrame.setDefaultLookAndFeelDecorated(true);
        app.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        app.frame.setJMenuBar(app.customerMenu());
        app.frame.add(app.customer());

        app.frame.setSize(new Dimension(app.frameWidth, app.frameHeight));
        app.frame.setVisible(true);
    }

    public JMenuBar menuBarCustomer = new JMenuBar();
    public JMenu menuCustomer = new JMenu("Customer");
    public JMenuItem itemBuy= new JMenuItem("Buy Products");
    public JMenu menuEmployee = new JMenu("Employee");
    public JMenuItem itemLogin = new JMenuItem("Login");

    public JMenuBar customerMenu(){
        menuCustomer.add(itemBuy);

        menuEmployee.add(itemLogin);

        menuBarCustomer.add(menuCustomer); menuBarCustomer.add(menuEmployee);
        return menuBarCustomer;
    }

    public JPanel pnlBtn = new JPanel(new FlowLayout());
    public JPanel pnlTxt = new JPanel(new GridLayout(6, 2));;
    public JPanel pnlMain = new JPanel();

    public JPanel pnlBuy = new JPanel(new BorderLayout());
    public JPanel pnlCartList = new JPanel(new GridLayout(2, 1));
    public JPanel pnlCart = new JPanel(new BorderLayout());
    public JPanel pnlSelectProduct = new JPanel(new BorderLayout());
    public JPanel pnlSPTop = new JPanel(new GridLayout(2, 1));
    private String[] arrColumnsProduct = {"ID", "Product", "Type", "Quantity", "Price", "Sale (%)", "Store"};
    public DefaultTableModel mdlCart = new DefaultTableModel(arrColumnsProduct, 0);
    public JTable tblCart = new JTable(mdlCart);
    public DefaultTableModel mdlSelect = new DefaultTableModel(arrColumnsProduct, 0);
    public JTable tblSelect = new JTable(mdlSelect);
    public JLabel lblCart = new JLabel("Shopping Cart", SwingConstants.CENTER);
    public JLabel lblProducts = new JLabel("Select Products", SwingConstants.CENTER);
    private String[] type = {"All", "Bakery", "Chocolate", "Dairy", "Fruit", "Grains", "Meat", "Sweets", "Vegetable"};
    public JComboBox<String> boxType = new JComboBox<>(type);
    public JButton btnAdd = new JButton("Add");
    public JButton btnRemove = new JButton("Remove");
    public JButton btnBuy = new JButton("Buy");

    public JPanel customer() {
        pnlCart.add(lblCart, BorderLayout.NORTH);
        pnlCart.add(new JScrollPane(tblCart), BorderLayout.CENTER);

        pnlSPTop.add(lblProducts);
        pnlSPTop.add(boxType);

        pnlSelectProduct.add(pnlSPTop, BorderLayout.NORTH);
        pnlSelectProduct.add(new JScrollPane(tblSelect), BorderLayout.CENTER);

        pnlCartList.add(pnlCart); pnlCartList.add(pnlSelectProduct);

        pnlBtn.add(btnAdd); pnlBtn.add(btnRemove); pnlBtn.add(btnBuy);

        pnlBuy.add(pnlCartList, BorderLayout.CENTER);
        pnlBuy.add(pnlBtn, BorderLayout.SOUTH);
        return pnlBuy;
    }

    public JMenuBar menuBar = new JMenuBar();
    public JMenu menuUser = new JMenu("Account");
    public JMenuItem itemLogOut = new JMenuItem("Log Out");
    public JMenu menuStore = new JMenu("Stores");
    public JMenuItem itemNewStore = new JMenuItem("New");
    public JMenuItem itemListStore = new JMenuItem("List");
    public JMenu menuProduct = new JMenu("Products");
    public JMenuItem itemNewProduct = new JMenuItem("New");
    public JMenuItem itemListProduct = new JMenuItem("List");
    public JMenuItem itemSale = new JMenuItem("Add Sale");
    public JMenu menuSettings = new JMenu("Settings");
    public JMenuItem itemSetFont = new JMenuItem("Font");
    public JMenuItem itemSetColor = new JMenuItem("Color");

    public JMenuBar createMenu(){
        menuUser.add(itemLogOut);

        menuStore.add(itemNewStore); menuStore.add(itemListStore);

        menuProduct.add(itemNewProduct); menuProduct.add(itemListProduct); menuProduct.add(itemSale);

        menuSettings.add(itemSetFont); menuSettings.add(itemSetColor);

        menuBar.add(menuUser); menuBar.add(menuStore); menuBar.add(menuProduct); menuBar.add(menuSettings);
        return menuBar;
    }

    public JPanel pnlCenter = new JPanel(new BorderLayout());
    public JPanel pnlText = new JPanel(new GridLayout(4, 2));
    public JPanel pnlLogin = new JPanel(new GridBagLayout());
    public JLabel lblLogin = new JLabel("Login", SwingConstants.CENTER);
    public JLabel lblUser = new JLabel("Username:", SwingConstants.CENTER);
    public JLabel lblPass = new JLabel("Password:", SwingConstants.CENTER);
    public JTextField txtUser = new JTextField();
    public JPasswordField txtPass = new JPasswordField();
    public JButton btnLogin = new JButton("Login");

    public JPanel login(){
        pnlText.add(new JLabel());pnlText.add(new JLabel());
        pnlText.add(lblUser); pnlText.add(txtUser);
        pnlText.add(lblPass); pnlText.add(txtPass);
        pnlText.add(new JLabel());pnlText.add(new JLabel());

        pnlBtn.removeAll();
        pnlBtn.add(btnLogin);

        pnlCenter.add(lblLogin, BorderLayout.NORTH);
        pnlCenter.add(pnlText, BorderLayout.CENTER);
        pnlCenter.add(pnlBtn, BorderLayout.SOUTH);

        pnlLogin.add(pnlCenter);
        return pnlLogin;
    }

    public JPanel pnlNewStore = new JPanel(new BorderLayout());
    public JLabel lblNewStore = new JLabel("Enter New Store", SwingConstants.CENTER);
    public JTextField txtStoreID = new JTextField();
    public JTextField txtStoreName = new JTextField();
    public JTextField txtStoreAddress = new JTextField();
    public JTextField txtStorePhone = new JTextField();
    public JButton btnStoreSave = new JButton("Save Store");
    public JLabel lblStoreID = new JLabel("Store ID:", SwingConstants.CENTER);
    public JLabel lblStoreName = new JLabel("Store Name:", SwingConstants.CENTER);
    public JLabel lblStoreAddress = new JLabel("Store Address:", SwingConstants.CENTER);
    public JLabel lblStorePhone = new JLabel("Store Phone Number:", SwingConstants.CENTER);

    public JPanel newStore(){
        pnlTxt.add(new JLabel()); pnlTxt.add(new JLabel());
        pnlTxt.add(lblStoreID); pnlTxt.add(txtStoreID);
        pnlTxt.add(lblStoreName); pnlTxt.add(txtStoreName);
        pnlTxt.add(lblStoreAddress); pnlTxt.add(txtStoreAddress);
        pnlTxt.add(lblStorePhone); pnlTxt.add(txtStorePhone);

        pnlBtn.add(btnStoreSave);

        pnlNewStore.add(lblNewStore, BorderLayout.NORTH);
        pnlNewStore.add(pnlTxt, BorderLayout.CENTER);
        pnlNewStore.add(pnlBtn, BorderLayout.SOUTH);
        return pnlNewStore;
    }

    public JPanel pnlListStore = new JPanel(new BorderLayout());
    private String[] arrColumnsStore = {"ID", "Name", "Address", "Phone Number"};
    public DefaultTableModel mdlStore = new DefaultTableModel(arrColumnsStore, 0);
    public JTable tblStore = new JTable(mdlStore);
    public JButton btnStoreDel = new JButton("Delete");
    public JLabel lblStoreList = new JLabel("Listed all stores", SwingConstants.CENTER);

    public JPanel listStore(){
        pnlBtn.add(btnStoreDel);

        pnlListStore.add(lblStoreList, BorderLayout.NORTH);
        pnlListStore.add(new JScrollPane(tblStore), BorderLayout.CENTER);
        pnlListStore.add(pnlBtn, BorderLayout.SOUTH);

        return pnlListStore;
    }

    public JPanel pnlNewProduct = new JPanel(new BorderLayout());
    public JLabel lblNewProduct = new JLabel("Enter new Product", SwingConstants.CENTER);
    public JTextField txtProductID = new JTextField();
    public JTextField txtProductName = new JTextField();
    private String[] types = {"", "Fruit", "Vegetable", "Grains", "Lactose", "Sweets", "Chocolate"};
    public JComboBox<String> boxProductType = new JComboBox<>(types);
    public JTextField txtProductQuantity = new JTextField();
    public JTextField txtProductPrice = new JTextField();
    public DefaultComboBoxModel<String> mdlBoxStore = new DefaultComboBoxModel<>();
    public JComboBox<String> boxStore = new JComboBox<>(mdlBoxStore);
    public JButton btnProductSave = new JButton("Save Product");
    public JLabel lblProductID = new JLabel("Product ID:", SwingConstants.CENTER);
    public JLabel lblProductName = new JLabel("Product Name:", SwingConstants.CENTER);
    public JLabel lblProductType = new JLabel("Product Type:", SwingConstants.CENTER);
    public JLabel lblProductQuantity = new JLabel("Product Quantity:", SwingConstants.CENTER);
    public JLabel lblProductPrice = new JLabel("Product Price:", SwingConstants.CENTER);
    public JLabel lblProductStore = new JLabel("Store it's in:", SwingConstants.CENTER);

    public JPanel newProduct(){
        txtStoreID.setPreferredSize(new Dimension(50, 15));

        pnlTxt.add(lblProductID); pnlTxt.add(txtProductID);
        pnlTxt.add(lblProductName); pnlTxt.add(txtProductName);
        pnlTxt.add(lblProductType); pnlTxt.add(boxProductType);
        pnlTxt.add(lblProductQuantity); pnlTxt.add(txtProductQuantity);
        pnlTxt.add(lblProductPrice); pnlTxt.add(txtProductPrice);
        pnlTxt.add(lblProductStore); pnlTxt.add(boxStore);

        pnlBtn.add(btnProductSave);

        pnlNewProduct.add(lblNewProduct, BorderLayout.NORTH);
        pnlNewProduct.add(pnlTxt, BorderLayout.CENTER);
        pnlNewProduct.add(pnlBtn, BorderLayout.SOUTH);
        return pnlNewProduct;
    }

    public JPanel pnlListProduct = new JPanel(new BorderLayout());
    public JLabel lblProductList = new JLabel("Listed all products", SwingConstants.CENTER);
    public DefaultTableModel mdlProduct = new DefaultTableModel(arrColumnsProduct, 0);
    public JTable tblProduct = new JTable(mdlProduct);
    public JButton btnAll = new JButton("All");
    public JButton btnMax = new JButton("Max");
    public JButton btnByStore = new JButton("By Store");
    public JButton btnShowSale = new JButton("On Sale");
    public JButton btnMostSold = new JButton("Most Sold");
    public JButton btnProductDel = new JButton("Delete");

    public JPanel listProduct(){
        pnlBtn.add(btnAll); pnlBtn.add(btnMax); pnlBtn.add(btnByStore); pnlBtn.add(btnShowSale); pnlBtn.add(btnMostSold); pnlBtn.add(btnProductDel);

        pnlListProduct.add(lblProductList, BorderLayout.NORTH);
        pnlListProduct.add(new JScrollPane(tblProduct), BorderLayout.CENTER);
        pnlListProduct.add(pnlBtn, BorderLayout.SOUTH);

        return pnlListProduct;
    }

    public JPanel pnlSale = new JPanel(new BorderLayout());
    public JPanel pnlNorth = new JPanel(new BorderLayout());
    public JLabel lblSale = new JLabel("Set Sale Percentage", SwingConstants.CENTER);
    public JPanel pnlProductInfo = new JPanel(new BorderLayout());
    public DefaultComboBoxModel<Integer> mdlBoxProduct = new DefaultComboBoxModel<>();
    public JComboBox<Integer> boxProduct = new JComboBox<>(mdlBoxProduct);
    public JTextField txtPercent = new JTextField();
    public JLabel lblPercent = new JLabel("Sale Percentage", SwingConstants.CENTER);
    public JLabel lblPrice = new JLabel("Price After Sale:", SwingConstants.CENTER);
    public JLabel lblChangedPrice = new JLabel("", SwingConstants.CENTER);
    public JButton btnSale = new JButton("Sale");

    public JPanel saleProduct(){
        pnlProductInfo.add(boxProduct, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tblProduct);
        scrollPane.setPreferredSize(new Dimension(400, 50));
        pnlProductInfo.add(scrollPane, BorderLayout.CENTER);

        pnlNorth.add(lblSale, BorderLayout.NORTH);
        pnlNorth.add(pnlProductInfo, BorderLayout.CENTER);

        pnlBtn.add(btnSale);

        pnlText.add(new JLabel()); pnlText.add(new JLabel());
        pnlText.add(lblPercent); pnlText.add(txtPercent);
        pnlText.add(lblPrice); pnlText.add(lblChangedPrice);
        pnlText.add(new JLabel()); pnlText.add(new JLabel());

        pnlSale.add(pnlNorth, BorderLayout.NORTH);
        pnlSale.add(pnlText, BorderLayout.CENTER);
        pnlSale.add(pnlBtn, BorderLayout.SOUTH);
        return pnlSale;
    }

    public JPanel pnlFontSettings = new JPanel(new BorderLayout());
    public JLabel lblSetFont = new JLabel("Font Settings", SwingConstants.CENTER);
    public JPanel pnlSelect = new JPanel(new GridLayout(8, 4));
    public JLabel lblTitleFont = new JLabel("Title Font", SwingConstants.CENTER);
    public JLabel lblLabelFont = new JLabel("Label Font", SwingConstants.CENTER);
    public JLabel lblTextFont = new JLabel("Text Font", SwingConstants.CENTER);
    public JLabel lblBtnFont = new JLabel("Button Font", SwingConstants.CENTER);
    public JLabel lblMenuFont = new JLabel("Menu Font", SwingConstants.CENTER);
    public JLabel lblMenuItemFont = new JLabel("Menu Item Font", SwingConstants.CENTER);
    public JLabel lblTableFont = new JLabel("Table Font", SwingConstants.CENTER);
    public JLabel lblTableHeadFont = new JLabel("Table Header Font", SwingConstants.CENTER);
    private String[] fonts = { "Arial", "Comic Sans MS", "SansSerif", "Times New Roman", "Verdana"};
    public JComboBox<String> boxTitleFont = new JComboBox<>(fonts);
    public JComboBox<String> boxLabelFont = new JComboBox<>(fonts);
    public JComboBox<String> boxTextFont = new JComboBox<>(fonts);
    public JComboBox<String> boxBtnFont = new JComboBox<>(fonts);
    public JComboBox<String> boxMenuFont = new JComboBox<>(fonts);
    public JComboBox<String> boxMenuItemFont = new JComboBox<>(fonts);
    public JComboBox<String> boxTableFont = new JComboBox<>(fonts);
    public JComboBox<String> boxTblHeadFont = new JComboBox<>(fonts);
    private String[] format = {"Plain", "Bold", "Italic"};
    public JComboBox<String> boxTitleFormat = new JComboBox<>(format);
    public JComboBox<String> boxLabelFormat = new JComboBox<>(format);
    public JComboBox<String> boxTextFormat = new JComboBox<>(format);
    public JComboBox<String> boxBtnFormat = new JComboBox<>(format);
    public JComboBox<String> boxMenuFormat = new JComboBox<>(format);
    public JComboBox<String> boxMenuItemFormat = new JComboBox<>(format);
    public JComboBox<String> boxTableFormat = new JComboBox<>(format);
    public JComboBox<String> boxTblHeadFormat = new JComboBox<>(format);
    public JTextField txtTitleSize = new JTextField();
    public JTextField txtLabelSize = new JTextField();
    public JTextField txtTextSize = new JTextField();
    public JTextField txtBtnSize = new JTextField();
    public JTextField txtMenuSize = new JTextField();
    public JTextField txtMenuItemSize = new JTextField();
    public JTextField txtTableSize = new JTextField();
    public JTextField txtTableHeadSize = new JTextField();
    public JButton btnFontSave = new JButton("Save");
    public JButton btnFontClear = new JButton("Clear");
    public JButton btnFontReset = new JButton("Reset");

    public JPanel setFont(){
        pnlBtn.add(btnFontSave); pnlBtn.add(btnFontClear); pnlBtn.add(btnFontReset);

        pnlSelect.add(lblTitleFont); pnlSelect.add(boxTitleFont); pnlSelect.add(boxTitleFormat); pnlSelect.add(txtTitleSize);
        pnlSelect.add(lblLabelFont); pnlSelect.add(boxLabelFont); pnlSelect.add(boxLabelFormat); pnlSelect.add(txtLabelSize);
        pnlSelect.add(lblTextFont); pnlSelect.add(boxTextFont); pnlSelect.add(boxTextFormat); pnlSelect.add(txtTextSize);
        pnlSelect.add(lblBtnFont); pnlSelect.add(boxBtnFont); pnlSelect.add(boxBtnFormat); pnlSelect.add(txtBtnSize);
        pnlSelect.add(lblMenuFont); pnlSelect.add(boxMenuFont); pnlSelect.add(boxMenuFormat); pnlSelect.add(txtMenuSize);
        pnlSelect.add(lblMenuItemFont); pnlSelect.add(boxMenuItemFont); pnlSelect.add(boxMenuItemFormat); pnlSelect.add(txtMenuItemSize);
        pnlSelect.add(lblTableFont); pnlSelect.add(boxTableFont); pnlSelect.add(boxTableFormat); pnlSelect.add(txtTableSize);
        pnlSelect.add(lblTableHeadFont); pnlSelect.add(boxTblHeadFont); pnlSelect.add(boxTblHeadFormat); pnlSelect.add(txtTableHeadSize);

        pnlFontSettings.add(lblSetFont, BorderLayout.NORTH);
        pnlFontSettings.add(pnlSelect, BorderLayout.CENTER);
        pnlFontSettings.add(pnlBtn, BorderLayout.SOUTH);
        return pnlFontSettings;
    }

    public JPanel pnlColorSettings = new JPanel(new BorderLayout());
    public JLabel lblSetColor = new JLabel("Color Settings", SwingConstants.CENTER);
    public JPanel pnlSelectColor = new JPanel(new GridLayout(15, 2));
    public JButton btnBackgroundColor = new JButton("Background Color");
    public JButton btnTitleColor = new JButton("Title Color");
    public JButton btnLabelColor = new JButton("Label Color");
    public JButton btnTextColor = new JButton("Text Color");
    public JButton btnTextBackColor = new JButton("Text Background Color");
    public JButton btnBtnColor = new JButton("Button Color");
    public JButton btnBtnBackColor = new JButton("Button Background Color");
    public JButton btnMenuColor = new JButton("Menu Color");
    public JButton btnMenuBackColor = new JButton("Menu Background Color");
    public JButton btnMenuItemColor = new JButton("Menu Item Color");
    public JButton btnMenuItemBackColor = new JButton("Menu Item Background Color");
    public JButton btnTableColor = new JButton("Table Color");
    public JButton btnTableBackColor = new JButton("Table Background Color");
    public JButton btnTableHeadColor = new JButton("Table Header Color");
    public JButton btnTableHeadBackColor = new JButton("Table Header Background Color");
    public JTextField txtBackgroundColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTitleColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtLabelColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTextColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTextBackColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtBtnColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtBtnBackColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtMenuColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtMenuBackColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtMenuItemColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtMenuItemBackColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTableColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTableBackColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTableHeadColor = new JTextField(SwingConstants.CENTER);
    public JTextField txtTableHeadBackColor = new JTextField(SwingConstants.CENTER);
    public JButton btnColorSave = new JButton("Save");
    public JButton btnColorClear = new JButton("Clear");
    public JButton btnColorReset = new JButton("Reset");

    public JPanel setColor(){
        pnlBtn.add(btnColorSave); pnlBtn.add(btnColorClear); pnlBtn.add(btnColorReset);

        pnlSelectColor.add(btnBackgroundColor);pnlSelectColor.add(txtBackgroundColor);
        pnlSelectColor.add(btnTitleColor);pnlSelectColor.add(txtTitleColor);
        pnlSelectColor.add(btnLabelColor);pnlSelectColor.add(txtLabelColor);
        pnlSelectColor.add(btnTextColor);pnlSelectColor.add(txtTextColor);
        pnlSelectColor.add(btnTextBackColor);pnlSelectColor.add(txtTextBackColor);
        pnlSelectColor.add(btnBtnColor);pnlSelectColor.add(txtBtnColor);
        pnlSelectColor.add(btnBtnBackColor);pnlSelectColor.add(txtBtnBackColor);
        pnlSelectColor.add(btnMenuColor);pnlSelectColor.add(txtMenuColor);
        pnlSelectColor.add(btnMenuBackColor);pnlSelectColor.add(txtMenuBackColor);
        pnlSelectColor.add(btnMenuItemColor);pnlSelectColor.add(txtMenuItemColor);
        pnlSelectColor.add(btnMenuItemBackColor);pnlSelectColor.add(txtMenuItemBackColor);
        pnlSelectColor.add(btnTableColor);pnlSelectColor.add(txtTableColor);
        pnlSelectColor.add(btnTableBackColor);pnlSelectColor.add(txtTableBackColor);
        pnlSelectColor.add(btnTableHeadColor);pnlSelectColor.add(txtTableHeadColor);
        pnlSelectColor.add(btnTableHeadBackColor);pnlSelectColor.add(txtTableHeadBackColor);

        pnlColorSettings.add(lblSetColor, BorderLayout.NORTH);
        pnlColorSettings.add(pnlSelectColor, BorderLayout.CENTER);
        pnlColorSettings.add(pnlBtn, BorderLayout.SOUTH);
        return pnlColorSettings;
    }

    //Font Settings

    public void setTitleFont(String font, int emphasis, int size){
        Font titleFont = new Font(font, emphasis, size);
        lblCart.setFont(titleFont);
        lblProducts.setFont(titleFont);
        lblLogin.setFont(titleFont);
        lblNewStore.setFont(titleFont);
        lblStoreList.setFont(titleFont);
        lblNewProduct.setFont(titleFont);
        lblProductList.setFont(titleFont);
        lblSale.setFont(titleFont);
        lblSetFont.setFont(titleFont);
        lblSetColor.setFont(titleFont);
    }

    public void setLabelFont(String font, int emphasis, int size){
        Font lblFont = new Font(font, emphasis, size);
        lblUser.setFont(lblFont);
        lblPass.setFont(lblFont);
        lblStoreID.setFont(lblFont);
        lblStoreName.setFont(lblFont);
        lblStoreAddress.setFont(lblFont);
        lblStorePhone.setFont(lblFont);
        lblProductID.setFont(lblFont);
        lblProductName.setFont(lblFont);
        lblProductType.setFont(lblFont);
        lblProductQuantity.setFont(lblFont);
        lblProductPrice.setFont(lblFont);
        lblProductStore.setFont(lblFont);
        lblPercent.setFont(lblFont);
        lblPrice.setFont(lblFont);
        lblTitleFont.setFont(lblFont);
        lblLabelFont.setFont(lblFont);
        lblTextFont.setFont(lblFont);
        lblBtnFont.setFont(lblFont);
        lblMenuFont.setFont(lblFont);
        lblMenuItemFont.setFont(lblFont);
        lblTableFont.setFont(lblFont);
        lblTableHeadFont.setFont(lblFont);
    }

    public void setTextFont(String font, int emphasis, int size){
        Font txtFont = new Font(font, emphasis, size);
        txtUser.setFont(txtFont);
        txtPass.setFont(txtFont);
        txtStoreID.setFont(txtFont);
        txtStoreName.setFont(txtFont);
        txtStoreAddress.setFont(txtFont);
        txtStorePhone.setFont(txtFont);
        txtProductID.setFont(txtFont);
        txtProductName.setFont(txtFont);
        txtProductQuantity.setFont(txtFont);
        txtProductPrice.setFont(txtFont);
        txtPercent.setFont(txtFont);
        txtTitleSize.setFont(txtFont);
        txtLabelSize.setFont(txtFont);
        txtTextSize.setFont(txtFont);
        txtBtnSize.setFont(txtFont);
        txtMenuSize.setFont(txtFont);
        txtMenuItemSize.setFont(txtFont);
        txtTableSize.setFont(txtFont);
        txtTableHeadSize.setFont(txtFont);
        txtBackgroundColor.setFont(txtFont);
        txtTitleColor.setFont(txtFont);
        txtLabelColor.setFont(txtFont);
        txtTextColor.setFont(txtFont);
        txtTextBackColor.setFont(txtFont);
        txtBtnColor.setFont(txtFont);
        txtBtnBackColor.setFont(txtFont);
        txtMenuColor.setFont(txtFont);
        txtMenuBackColor.setFont(txtFont);
        txtMenuItemColor.setFont(txtFont);
        txtMenuItemBackColor.setFont(txtFont);
        txtTableColor.setFont(txtFont);
        txtTableBackColor.setFont(txtFont);
        txtTableHeadColor.setFont(txtFont);
        txtTableHeadBackColor.setFont(txtFont);

        boxType.setFont(txtFont);
        boxStore.setFont(txtFont);
        boxProduct.setFont(txtFont);
        boxProductType.setFont(txtFont);
        boxTitleFont.setFont(txtFont);
        boxLabelFont.setFont(txtFont);
        boxTextFont.setFont(txtFont);
        boxBtnFont.setFont(txtFont);
        boxMenuFont.setFont(txtFont);
        boxMenuItemFont.setFont(txtFont);
        boxTableFont.setFont(txtFont);
        boxTblHeadFont.setFont(txtFont);
        boxTitleFormat.setFont(txtFont);
        boxLabelFormat.setFont(txtFont);
        boxTextFormat.setFont(txtFont);
        boxBtnFormat.setFont(txtFont);
        boxMenuFormat.setFont(txtFont);
        boxMenuItemFormat.setFont(txtFont);
        boxTableFormat.setFont(txtFont);
        boxTblHeadFormat.setFont(txtFont);

        lblChangedPrice.setFont(txtFont);
    }

    public void setButtonFont(String font, int emphasis, int size){
        Font btnFont = new Font(font, emphasis, size);
        btnAdd.setFont(btnFont);
        btnRemove.setFont(btnFont);
        btnBuy.setFont(btnFont);
        btnLogin.setFont(btnFont);
        btnStoreSave.setFont(btnFont);
        btnProductSave.setFont(btnFont);
        btnStoreDel.setFont(btnFont);
        btnAll.setFont(btnFont);
        btnMax.setFont(btnFont);
        btnByStore.setFont(btnFont);
        btnMostSold.setFont(btnFont);
        btnShowSale.setFont(btnFont);
        btnProductDel.setFont(btnFont);
        btnSale.setFont(btnFont);
        btnFontSave.setFont(btnFont);
        btnFontClear.setFont(btnFont);
        btnFontReset.setFont(btnFont);
        btnBackgroundColor.setFont(btnFont);
        btnTitleColor.setFont(btnFont);
        btnLabelColor.setFont(btnFont);
        btnTextColor.setFont(btnFont);
        btnTextBackColor.setFont(btnFont);
        btnBtnColor.setFont(btnFont);
        btnBtnBackColor.setFont(btnFont);
        btnMenuColor.setFont(btnFont);
        btnMenuBackColor.setFont(btnFont);
        btnMenuItemColor.setFont(btnFont);
        btnMenuItemBackColor.setFont(btnFont);
        btnTableColor.setFont(btnFont);
        btnTableBackColor.setFont(btnFont);
        btnTableHeadColor.setFont(btnFont);
        btnTableHeadBackColor.setFont(btnFont);
        btnTitleColor.setFont(btnFont);
        btnColorSave.setFont(btnFont);
        btnColorClear.setFont(btnFont);
        btnColorReset.setFont(btnFont);
    }

    public void setMenuFont(String font, int emphasis, int size){
        Font menuFont = new Font(font, emphasis, size);
        menuCustomer.setFont(menuFont);
        menuEmployee.setFont(menuFont);
        menuUser.setFont(menuFont);
        menuStore.setFont(menuFont);
        menuProduct.setFont(menuFont);
        menuSettings.setFont(menuFont);
    }

    public void setMenuItemFont(String font, int emphasis, int size){
        Font menuItemFont = new Font(font, emphasis, size);
        itemBuy.setFont(menuItemFont);
        itemLogin.setFont(menuItemFont);
        itemLogOut.setFont(menuItemFont);
        itemNewStore.setFont(menuItemFont);
        itemListStore.setFont(menuItemFont);
        itemNewProduct.setFont(menuItemFont);
        itemListProduct.setFont(menuItemFont);
        itemSale.setFont(menuItemFont);
        itemSetFont.setFont(menuItemFont);
        itemSetColor.setFont(menuItemFont);
    }

    public void setTableHeaderFont(String font, int emphasis, int size){
        Font tblHeaderFont = new Font(font, emphasis, size);
        tblCart.getTableHeader().setFont(tblHeaderFont);
        tblSelect.getTableHeader().setFont(tblHeaderFont);
        tblStore.getTableHeader().setFont(tblHeaderFont);
        tblProduct.getTableHeader().setFont(tblHeaderFont);
    }

    public void setTableFont(String font, int emphasis, int size){
        Font tblFont = new Font(font, emphasis, size);
        tblCart.setFont(tblFont);
        tblSelect.setFont(tblFont);
        tblStore.setFont(tblFont);
        tblProduct.setFont(tblFont);
    }

    //Color Methods

    public void setBackgroundColor(int rgb){
        Color color = new Color(rgb);
        pnlBuy.setBackground(color);
        pnlCartList.setBackground(color);
        pnlCart.setBackground(color);
        pnlSelectProduct.setBackground(color);
        pnlSPTop.setBackground(color);
        pnlLogin.setBackground(color);
        pnlTxt.setBackground(color);
        pnlCenter.setBackground(color);
        pnlMain.setBackground(color);
        pnlNewStore.setBackground(color);
        pnlListStore.setBackground(color);
        pnlNewProduct.setBackground(color);
        pnlListProduct.setBackground(color);
        pnlSale.setBackground(color);
        pnlBtn.setBackground(color);
        pnlTxt.setBackground(color);
        pnlSelect.setBackground(color);
        pnlNorth.setBackground(color);
        pnlFontSettings.setBackground(color);
        pnlColorSettings.setBackground(color);
        pnlProductInfo.setBackground(color);
        pnlText.setBackground(color);
    }

    public void setTitleColor(int rgb){
        Color color = new Color(rgb);
        lblCart.setForeground(color);
        lblProducts.setForeground(color);
        lblLogin.setForeground(color);
        lblNewStore.setForeground(color);
        lblStoreList.setForeground(color);
        lblNewProduct.setForeground(color);
        lblProductList.setForeground(color);
        lblSale.setForeground(color);
        lblSetFont.setForeground(color);
        lblSetColor.setForeground(color);
    }

    public void setLabelColor(int rgb){
        Color color = new Color(rgb);
        lblUser.setForeground(color);
        lblPass.setForeground(color);
        lblStoreID.setForeground(color);
        lblStoreName.setForeground(color);
        lblStoreAddress.setForeground(color);
        lblStorePhone.setForeground(color);
        lblProductID.setForeground(color);
        lblProductName.setForeground(color);
        lblProductType.setForeground(color);
        lblProductQuantity.setForeground(color);
        lblProductPrice.setForeground(color);
        lblProductStore.setForeground(color);
        lblPercent.setForeground(color);
        lblPrice.setForeground(color);
        lblTitleFont.setForeground(color);
        lblLabelFont.setForeground(color);
        lblTextFont.setForeground(color);
        lblBtnFont.setForeground(color);
        lblMenuFont.setForeground(color);
        lblMenuItemFont.setForeground(color);
        lblTableFont.setForeground(color);
        lblTableHeadFont.setForeground(color);
        lblChangedPrice.setForeground(color);
    }

    public void setTextColor(int rgb){
        Color color = new Color(rgb);
        txtUser.setForeground(color);
        txtPass.setForeground(color);
        txtStoreID.setForeground(color);
        txtStoreName.setForeground(color);
        txtStoreAddress.setForeground(color);
        txtStorePhone.setForeground(color);
        txtProductID.setForeground(color);
        txtProductName.setForeground(color);
        txtProductQuantity.setForeground(color);
        txtProductPrice.setForeground(color);
        txtPercent.setForeground(color);
        txtTitleSize.setForeground(color);
        txtLabelSize.setForeground(color);
        txtTextSize.setForeground(color);
        txtBtnSize.setForeground(color);
        txtMenuSize.setForeground(color);
        txtMenuItemSize.setForeground(color);
        txtTableSize.setForeground(color);
        txtTableHeadSize.setForeground(color);
        txtBackgroundColor.setForeground(color);
        txtTitleColor.setForeground(color);
        txtLabelColor.setForeground(color);
        txtTextColor.setForeground(color);
        txtTextBackColor.setForeground(color);
        txtBtnColor.setForeground(color);
        txtBtnBackColor.setForeground(color);
        txtMenuColor.setForeground(color);
        txtMenuBackColor.setForeground(color);
        txtMenuItemColor.setForeground(color);
        txtMenuItemBackColor.setForeground(color);
        txtTableColor.setForeground(color);
        txtTableBackColor.setForeground(color);
        txtTableHeadColor.setForeground(color);
        txtTableHeadBackColor.setForeground(color);

        boxType.setForeground(color);
        boxStore.setForeground(color);
        boxProduct.setForeground(color);
        boxProductType.setForeground(color);
        boxTitleFont.setForeground(color);
        boxLabelFont.setForeground(color);
        boxTextFont.setForeground(color);
        boxBtnFont.setForeground(color);
        boxMenuFont.setForeground(color);
        boxMenuItemFont.setForeground(color);
        boxTableFont.setForeground(color);
        boxTblHeadFont.setForeground(color);
        boxTitleFormat.setForeground(color);
        boxLabelFormat.setForeground(color);
        boxTextFormat.setForeground(color);
        boxBtnFormat.setForeground(color);
        boxMenuFormat.setForeground(color);
        boxMenuItemFormat.setForeground(color);
        boxTableFormat.setForeground(color);
        boxTblHeadFormat.setForeground(color);
    }

    public void setTextBackColor(int rgb){
        Color color = new Color(rgb);
        txtUser.setBackground(color);
        txtPass.setBackground(color);
        txtStoreID.setBackground(color);
        txtStoreName.setBackground(color);
        txtStoreAddress.setBackground(color);
        txtStorePhone.setBackground(color);
        txtProductID.setBackground(color);
        txtProductName.setBackground(color);
        txtProductQuantity.setBackground(color);
        txtProductPrice.setBackground(color);
        txtPercent.setBackground(color);
        txtTitleSize.setBackground(color);
        txtLabelSize.setBackground(color);
        txtTextSize.setBackground(color);
        txtBtnSize.setBackground(color);
        txtMenuSize.setBackground(color);
        txtMenuItemSize.setBackground(color);
        txtTableSize.setBackground(color);
        txtTableHeadSize.setBackground(color);

        boxType.setBackground(color);
        boxStore.setBackground(color);
        boxProduct.setBackground(color);
        boxProductType.setBackground(color);
        boxTitleFont.setBackground(color);
        boxLabelFont.setBackground(color);
        boxTextFont.setBackground(color);
        boxBtnFont.setBackground(color);
        boxMenuFont.setBackground(color);
        boxMenuItemFont.setBackground(color);
        boxTableFont.setBackground(color);
        boxTblHeadFont.setBackground(color);
        boxTitleFormat.setBackground(color);
        boxLabelFormat.setBackground(color);
        boxTextFormat.setBackground(color);
        boxBtnFormat.setBackground(color);
        boxMenuFormat.setBackground(color);
        boxMenuItemFormat.setBackground(color);
        boxTableFormat.setBackground(color);
        boxTblHeadFormat.setBackground(color);
    }

    public void setButtonColor(int rgb){
        Color color = new Color(rgb);
        btnAdd.setForeground(color);
        btnRemove.setForeground(color);
        btnBuy.setForeground(color);
        btnLogin.setForeground(color);
        btnStoreSave.setForeground(color);
        btnProductSave.setForeground(color);
        btnStoreDel.setForeground(color);
        btnAll.setForeground(color);
        btnMax.setForeground(color);
        btnByStore.setForeground(color);
        btnMostSold.setForeground(color);
        btnShowSale.setForeground(color);
        btnProductDel.setForeground(color);
        btnSale.setForeground(color);
        btnFontSave.setForeground(color);
        btnFontClear.setForeground(color);
        btnFontReset.setForeground(color);
        btnBackgroundColor.setForeground(color);
        btnTitleColor.setForeground(color);
        btnLabelColor.setForeground(color);
        btnTextColor.setForeground(color);
        btnTextBackColor.setForeground(color);
        btnBtnColor.setForeground(color);
        btnBtnBackColor.setForeground(color);
        btnMenuColor.setForeground(color);
        btnMenuBackColor.setForeground(color);
        btnMenuItemColor.setForeground(color);
        btnMenuItemBackColor.setForeground(color);
        btnTableColor.setForeground(color);
        btnTableBackColor.setForeground(color);
        btnTableHeadColor.setForeground(color);
        btnTableHeadBackColor.setForeground(color);
        btnTitleColor.setForeground(color);
        btnColorSave.setForeground(color);
        btnColorClear.setForeground(color);
        btnColorReset.setForeground(color);
    }

    public void setButtonBackColor(int rgb){
        Color color = new Color(rgb);
        btnAdd.setBackground(color);
        btnRemove.setBackground(color);
        btnBuy.setBackground(color);
        btnLogin.setBackground(color);
        btnStoreSave.setBackground(color);
        btnProductSave.setBackground(color);
        btnStoreDel.setBackground(color);
        btnAll.setBackground(color);
        btnMax.setBackground(color);
        btnByStore.setBackground(color);
        btnMostSold.setBackground(color);
        btnShowSale.setBackground(color);
        btnProductDel.setBackground(color);
        btnSale.setBackground(color);
        btnFontSave.setBackground(color);
        btnFontClear.setBackground(color);
        btnFontReset.setBackground(color);
        btnBackgroundColor.setBackground(color);
        btnTitleColor.setBackground(color);
        btnLabelColor.setBackground(color);
        btnTextColor.setBackground(color);
        btnTextBackColor.setBackground(color);
        btnBtnColor.setBackground(color);
        btnBtnBackColor.setBackground(color);
        btnMenuColor.setBackground(color);
        btnMenuBackColor.setBackground(color);
        btnMenuItemColor.setBackground(color);
        btnMenuItemBackColor.setBackground(color);
        btnTableColor.setBackground(color);
        btnTableBackColor.setBackground(color);
        btnTableHeadColor.setBackground(color);
        btnTableHeadBackColor.setBackground(color);
        btnTitleColor.setBackground(color);
        btnColorSave.setBackground(color);
        btnColorClear.setBackground(color);
        btnColorReset.setBackground(color);
    }

    public void setMenuBackColor(int rgb){
        Color color = new Color(rgb);
        menuBar.setBackground(color);
        menuBarCustomer.setBackground(color);
    }

    public void setMenuColor(int rgb){
        Color color = new Color(rgb);
        menuCustomer.setForeground(color);
        menuEmployee.setForeground(color);
        menuUser.setForeground(color);
        menuStore.setForeground(color);
        menuProduct.setForeground(color);
        menuSettings.setForeground(color);
    }

    public void setMenuItemColor(int rgb){
        Color color = new Color(rgb);
        itemBuy.setForeground(color);
        itemLogin.setForeground(color);
        itemLogOut.setForeground(color);
        itemNewStore.setForeground(color);
        itemListStore.setForeground(color);
        itemNewProduct.setForeground(color);
        itemListProduct.setForeground(color);
        itemSale.setForeground(color);
        itemSetFont.setForeground(color);
        itemSetColor.setForeground(color);
    }

    public void setMenuItemBackColor(int rgb){
        Color color = new Color(rgb);
        itemBuy.setBackground(color);
        itemLogin.setBackground(color);
        itemLogOut.setBackground(color);
        itemNewStore.setBackground(color);
        itemListStore.setBackground(color);
        itemNewProduct.setBackground(color);
        itemListProduct.setBackground(color);
        itemSale.setBackground(color);
        itemSetFont.setBackground(color);
        itemSetColor.setBackground(color);
    }

    public void setTableColor(int rgb){
        Color color = new Color(rgb);
        tblCart.setForeground(color);
        tblSelect.setForeground(color);
        tblStore.setForeground(color);
        tblProduct.setForeground(color);
    }

    public void setTableBackColor(int rgb){
        Color color = new Color(rgb);
        tblCart.setBackground(color);
        tblSelect.setBackground(color);
        tblStore.setBackground(color);
        tblProduct.setBackground(color);
    }

    public void setTableHeaderColor(int rgb){
        Color color = new Color(rgb);
        tblCart.getTableHeader().setForeground(color);
        tblSelect.getTableHeader().setForeground(color);
        tblStore.getTableHeader().setForeground(color);
        tblProduct.getTableHeader().setForeground(color);
    }

    public void setTableHeaderBackColor(int rgb){
        Color color = new Color(rgb);
        tblCart.getTableHeader().setBackground(color);
        tblSelect.getTableHeader().setBackground(color);
        tblStore.getTableHeader().setBackground(color);
        tblProduct.getTableHeader().setBackground(color);
    }
}

/*
    Use a MenuBar to open different pages
    Page to enter a new store:
        1. ID
        2. Name
        3. Address
        4. Phone number
    Page to enter a new product:
        1. ID
        2. Name
        3. Type (combobox)
        4. Quantity
        5. Price
        6. Store it is in (combobox)
    Page to show products:
        1. Add sort by column
        2. Show maximum of an item in a store by its id
        3. Show total of products in a store by id
        4. Show products with reduced prices
        5. Most sold products
        (2-4 buttons with joptionpane);
    Page to put items on sale
    Add settings for customization
        1. Change background, buttons, table color
        2. Change font
*/