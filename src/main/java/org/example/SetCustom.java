package org.example;

import javax.swing.*;
import java.awt.*;

public class SetCustom {
    private int[] colors = {-1118482, -15856114, -1, -3482644};
    private String[] size = {"24", "18", "16"};
    private String baseFont = "SansSerif";

    public void removePanelComponents(StoreView view){
        if(view.pnlBtn.getComponentCount() > 0){
            view.pnlBtn.removeAll();
        }
        if(view.pnlTxt.getComponentCount() > 0){
            view.pnlTxt.removeAll();
        }
        if(view.pnlSelect.getComponentCount() > 0){
            view.pnlSelect.removeAll();
        }
        if(view.pnlNorth.getComponentCount() > 0){
            view.pnlNorth.removeAll();
        }
    }

    public void setCurrentFont(StoreView view, ExtractCustomization custom){
        String[] atributes;
        atributes = custom.getFont("Title");
        view.boxTitleFont.setSelectedItem(atributes[0]); view.boxTitleFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtTitleSize.setText(atributes[2]);
        atributes = custom.getFont("Label");
        view.boxLabelFont.setSelectedItem(atributes[0]); view.boxLabelFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtLabelSize.setText(atributes[2]);
        atributes = custom.getFont("Text");
        view.boxTextFont.setSelectedItem(atributes[0]); view.boxTextFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtTextSize.setText(atributes[2]);
        atributes = custom.getFont("Button");
        view.boxBtnFont.setSelectedItem(atributes[0]); view.boxBtnFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtBtnSize.setText(atributes[2]);
        atributes = custom.getFont("Menu");
        view.boxMenuFont.setSelectedItem(atributes[0]); view.boxMenuFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtMenuSize.setText(atributes[2]);
        atributes = custom.getFont("Menu Item");
        view.boxMenuItemFont.setSelectedItem(atributes[0]); view.boxMenuItemFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtMenuItemSize.setText(atributes[2]);
        atributes = custom.getFont("Table");
        view.boxTableFont.setSelectedItem(atributes[0]); view.boxTableFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtTableSize.setText(atributes[2]);
        atributes = custom.getFont("Table Header");
        view.boxTblHeadFont.setSelectedItem(atributes[0]); view.boxTblHeadFormat.setSelectedIndex(Integer.parseInt(atributes[1])); view.txtTableHeadSize.setText(atributes[2]);
    }

    public void resetFont(StoreView view){
        view.boxTitleFont.setSelectedItem(baseFont); view.boxTitleFormat.setSelectedItem("Bold"); view.txtTitleSize.setText(size[0]);
        view.boxLabelFont.setSelectedItem(baseFont); view.boxLabelFormat.setSelectedItem("Bold"); view.txtLabelSize.setText(size[1]);
        view.boxTextFont.setSelectedItem(baseFont); view.boxTextFormat.setSelectedItem("Plain"); view.txtTextSize.setText(size[2]);
        view.boxBtnFont.setSelectedItem(baseFont); view.boxBtnFormat.setSelectedItem("Plain"); view.txtBtnSize.setText(size[2]);
        view.boxMenuFont.setSelectedItem(baseFont); view.boxMenuFormat.setSelectedItem("Bold"); view.txtMenuSize.setText(size[1]);
        view.boxMenuItemFont.setSelectedItem(baseFont); view.boxMenuItemFormat.setSelectedItem("Plain"); view.txtMenuItemSize.setText(size[2]);
        view.boxTableFont.setSelectedItem(baseFont); view.boxTableFormat.setSelectedItem("Plain"); view.txtTableSize.setText(size[2]);
        view.boxTblHeadFont.setSelectedItem(baseFont); view.boxTblHeadFormat.setSelectedItem("Bold"); view.txtTableHeadSize.setText(size[1]);
    }

    public void setCurrentColor(StoreView view, ExtractCustomization custom){
        int rgb = custom.getColor("Background");
        view.txtBackgroundColor.setForeground(new Color(textColor(rgb)));
        view.txtBackgroundColor.setText(rgb+"");
        view.txtBackgroundColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Title");
        view.txtTitleColor.setForeground(new Color(textColor(rgb)));
        view.txtTitleColor.setText(rgb+"");
        view.txtTitleColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Label");
        view.txtLabelColor.setForeground(new Color(textColor(rgb)));
        view.txtLabelColor.setText(rgb+"");
        view.txtLabelColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Text");
        view.txtTextColor.setForeground(new Color(textColor(rgb)));
        view.txtTextColor.setText(rgb+"");
        view.txtTextColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Text Background");
        view.txtTextBackColor.setForeground(new Color(textColor(rgb)));
        view.txtTextBackColor.setText(rgb+"");
        view.txtTextBackColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Button");
        view.txtBtnColor.setForeground(new Color(textColor(rgb)));
        view.txtBtnColor.setText(rgb+"");
        view.txtBtnColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Button Background");
        view.txtBtnBackColor.setForeground(new Color(textColor(rgb)));
        view.txtBtnBackColor.setText(rgb+"");
        view.txtBtnBackColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Menu");
        view.txtMenuColor.setForeground(new Color(textColor(rgb)));
        view.txtMenuColor.setText(rgb+"");
        view.txtMenuColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Menu Background");
        view.txtMenuBackColor.setForeground(new Color(textColor(rgb)));
        view.txtMenuBackColor.setText(rgb+"");
        view.txtMenuBackColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Menu Item");
        view.txtMenuItemColor.setForeground(new Color(textColor(rgb)));
        view.txtMenuItemColor.setText(rgb+"");
        view.txtMenuItemColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Menu Item Background");
        view.txtMenuItemBackColor.setForeground(new Color(textColor(rgb)));
        view.txtMenuItemBackColor.setText(rgb+"");
        view.txtMenuItemBackColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Table");
        view.txtTableColor.setForeground(new Color(textColor(rgb)));
        view.txtTableColor.setText(rgb+"");
        view.txtTableColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Table Background");
        view.txtTableBackColor.setForeground(new Color(textColor(rgb)));
        view.txtTableBackColor.setText(rgb+"");
        view.txtTableBackColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Table Header");
        view.txtTableHeadColor.setForeground(new Color(textColor(rgb)));
        view.txtTableHeadColor.setText(rgb+"");
        view.txtTableHeadColor.setBackground(new Color(rgb));

        rgb = custom.getColor("Table Header Background");
        view.txtTableHeadBackColor.setForeground(new Color(textColor(rgb)));
        view.txtTableHeadBackColor.setText(rgb+"");
        view.txtTableHeadBackColor.setBackground(new Color(rgb));
    }

    public void resetColor(StoreView view){
        view.txtBackgroundColor.setText(colors[0]+"");
        view.txtBackgroundColor.setBackground(new Color(colors[0]));

        view.txtTitleColor.setText(colors[1]+"");
        view.txtTitleColor.setBackground(new Color(colors[1]));

        view.txtLabelColor.setText(colors[1]+"");
        view.txtLabelColor.setBackground(new Color(colors[1]));

        view.txtTextColor.setText(colors[1]+"");
        view.txtTextColor.setBackground(new Color(colors[1]));

        view.txtTextBackColor.setText(colors[2]+"");
        view.txtTextBackColor.setBackground(new Color(colors[2]));

        view.txtBtnColor.setText(colors[1]+"");
        view.txtBtnColor.setBackground(new Color(colors[1]));

        view.txtBtnBackColor.setText(colors[3]+"");
        view.txtBtnBackColor.setBackground(new Color(colors[3]));

        view.txtMenuColor.setText(colors[1]+"");
        view.txtMenuColor.setBackground(new Color(colors[1]));

        view.txtMenuBackColor.setText(colors[0]+"");
        view.txtMenuBackColor.setBackground(new Color(colors[0]));

        view.txtMenuItemColor.setText(colors[1]+"");
        view.txtMenuItemColor.setBackground(new Color(colors[1]));

        view.txtMenuItemBackColor.setText(colors[0]+"");
        view.txtMenuItemBackColor.setBackground(new Color(colors[0]));

        view.txtTableColor.setText(colors[1]+"");
        view.txtTableColor.setBackground(new Color(colors[1]));

        view.txtTableBackColor.setText(colors[0]+"");
        view.txtTableBackColor.setBackground(new Color(colors[0]));

        view.txtTableHeadColor.setText(colors[1]+"");
        view.txtTableHeadColor.setBackground(new Color(colors[1]));

        view.txtTableHeadBackColor.setText(colors[0]+"");
        view.txtTableHeadBackColor.setBackground(new Color(colors[0]));
    }

    public void setSettings(StoreView view, ExtractCustomization custom){
        String[] atributes;
        atributes = custom.getFont("Title");
        view.setTitleFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Label");
        view.setLabelFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Text");
        view.setTextFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Button");
        view.setButtonFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Menu");
        view.setMenuFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Menu Item");
        view.setMenuItemFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Table");
        view.setTableFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));
        atributes = custom.getFont("Table Header");
        view.setTableHeaderFont(atributes[0], Integer.parseInt(atributes[1]), Integer.parseInt(atributes[2]));

        view.setBackgroundColor(custom.getColor("Background"));
        view.setTitleColor(custom.getColor("Title"));
        view.setLabelColor(custom.getColor("Label"));
        view.setTextColor(custom.getColor("Text"));
        view.setTextBackColor(custom.getColor("Text Background"));
        view.setButtonColor(custom.getColor("Button"));
        view.setButtonBackColor(custom.getColor("Button Background"));
        view.setMenuColor(custom.getColor("Menu"));
        view.setMenuBackColor(custom.getColor("Menu Background"));
        view.setMenuItemColor(custom.getColor("Menu Item"));
        view.setMenuItemBackColor(custom.getColor("Menu Item Background"));
        view.setTableColor(custom.getColor("Table"));
        view.setTableBackColor(custom.getColor("Table Background"));
        view.setTableHeaderColor(custom.getColor("Table Header"));
        view.setTableHeaderBackColor(custom.getColor("Table Header Background"));
    }

    public void saveFonts(StoreView view, ExtractCustomization custom){
        if(!view.boxTitleFont.getSelectedItem().toString().isEmpty() && !view.boxTitleFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtTitleSize.getText())){
            custom.updateFont("Title", view.boxTitleFont.getSelectedItem().toString(), view.boxTitleFormat.getSelectedItem().toString(), view.txtTitleSize.getText());
        }
        if(!view.boxLabelFont.getSelectedItem().toString().isEmpty() && !view.boxLabelFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtLabelSize.getText())) {
            custom.updateFont("Label", view.boxLabelFont.getSelectedItem().toString(), view.boxLabelFormat.getSelectedItem().toString(), view.txtLabelSize.getText());
        }
        if(!view.boxTextFont.getSelectedItem().toString().isEmpty() && !view.boxTextFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtTextSize.getText())){
            custom.updateFont("Text", view.boxTextFont.getSelectedItem().toString(), view.boxTextFormat.getSelectedItem().toString(), view.txtTextSize.getText());
        }
        if(!view.boxBtnFont.getSelectedItem().toString().isEmpty() && !view.boxBtnFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtBtnSize.getText())){
            custom.updateFont("Button", view.boxBtnFont.getSelectedItem().toString(), view.boxBtnFormat.getSelectedItem().toString(), view.txtBtnSize.getText());
        }
        if(!view.boxMenuFont.getSelectedItem().toString().isEmpty() && !view.boxMenuFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtMenuSize.getText())){
            custom.updateFont("Menu", view.boxMenuFont.getSelectedItem().toString(), view.boxMenuFormat.getSelectedItem().toString(), view.txtMenuSize.getText());
        }
        if(!view.boxMenuItemFont.getSelectedItem().toString().isEmpty() && !view.boxMenuItemFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtMenuItemSize.getText())) {
            custom.updateFont("Menu Item", view.boxMenuItemFont.getSelectedItem().toString(), view.boxMenuItemFormat.getSelectedItem().toString(), view.txtMenuItemSize.getText());
        }
        if(!view.boxTableFont.getSelectedItem().toString().isEmpty() && !view.boxTableFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtTableSize.getText())){
            custom.updateFont("Table", view.boxTableFont.getSelectedItem().toString(), view.boxTableFormat.getSelectedItem().toString(), view.txtTableSize.getText());
        }
        if(!view.boxTblHeadFont.getSelectedItem().toString().isEmpty() && !view.boxTblHeadFormat.getSelectedItem().toString().isEmpty() && checkText(view.txtTableHeadSize.getText())){
            custom.updateFont("Table Header", view.boxTblHeadFont.getSelectedItem().toString(), view.boxTblHeadFormat.getSelectedItem().toString(), view.txtTableHeadSize.getText());
        }
    }

    public void saveColors(StoreView view, ExtractCustomization custom){
        if(checkText(view.txtBackgroundColor.getText())){
            custom.updateColor("Background", Integer.parseInt(view.txtBackgroundColor.getText()));
        }
        if(checkText(view.txtTitleColor.getText())){
            custom.updateColor("Title", Integer.parseInt(view.txtTitleColor.getText()));
        }
        if(checkText(view.txtLabelColor.getText())){
            custom.updateColor("Label", Integer.parseInt(view.txtLabelColor.getText()));
        }
        if(checkText(view.txtTextColor.getText())){
            custom.updateColor("Text", Integer.parseInt(view.txtTextColor.getText()));
        }
        if(checkText(view.txtTextBackColor.getText())){
            custom.updateColor("Text Background", Integer.parseInt(view.txtTextBackColor.getText()));
        }
        if(checkText(view.txtBtnColor.getText())){
            custom.updateColor("Button", Integer.parseInt(view.txtBtnColor.getText()));
        }
        if(checkText(view.txtBtnBackColor.getText())){
            custom.updateColor("Button Background", Integer.parseInt(view.txtBtnBackColor.getText()));
        }
        if(checkText(view.txtMenuColor.getText())){
            custom.updateColor("Menu", Integer.parseInt(view.txtMenuColor.getText()));
        }
        if(checkText(view.txtMenuBackColor.getText())){
            custom.updateColor("Menu Background", Integer.parseInt(view.txtMenuBackColor.getText()));
        }
        if(checkText(view.txtMenuItemColor.getText())){
            custom.updateColor("Menu Item", Integer.parseInt(view.txtMenuItemColor.getText()));
        }
        if(checkText(view.txtMenuItemBackColor.getText())){
            custom.updateColor("Menu Item Background", Integer.parseInt(view.txtMenuItemBackColor.getText()));
        }
        if(checkText(view.txtTableColor.getText())){
            custom.updateColor("Table", Integer.parseInt(view.txtTableColor.getText()));
        }
        if(checkText(view.txtTableBackColor.getText())){
            custom.updateColor("Table Background", Integer.parseInt(view.txtTableBackColor.getText()));
        }
        if(checkText(view.txtTableHeadColor.getText())){
            custom.updateColor("Table Header", Integer.parseInt(view.txtTableHeadColor.getText()));
        }
        if(checkText(view.txtTableHeadBackColor.getText())){
            custom.updateColor("Table Header Background", Integer.parseInt(view.txtTableHeadBackColor.getText()));
        }
    }

    public void resetText (StoreView view){
        view.txtUser.setText("");
        view.txtPass.setText("");
        view.txtStoreID.setText("");
        view.txtStoreName.setText("");
        view.txtStoreAddress.setText("");
        view.txtStorePhone.setText("");
        view.txtProductID.setText("");
        view.txtProductName.setText("");
        view.txtProductQuantity.setText("");
        view.txtProductPrice.setText("");
        view.txtPercent.setText("");
        view.txtTitleSize.setText("");
        view.txtLabelSize.setText("");
        view.txtTextSize.setText("");
        view.txtBtnSize.setText("");
        view.txtMenuSize.setText("");
        view.txtMenuItemSize.setText("");
        view.txtTableSize.setText("");
        view.txtTableHeadSize.setText("");
        view.txtBackgroundColor.setText("");
        view.txtTitleColor.setText("");
        view.txtLabelColor.setText("");
        view.txtTextColor.setText("");
        view.txtTextBackColor.setText("");
        view.txtBtnColor.setText("");
        view.txtBtnBackColor.setText("");
        view.txtMenuColor.setText("");
        view.txtMenuBackColor.setText("");
        view.txtMenuItemColor.setText("");
        view.txtMenuItemBackColor.setText("");
        view.txtTableColor.setText("");
        view.txtTableBackColor.setText("");
        view.txtTableHeadColor.setText("");
        view.txtTableHeadBackColor.setText("");

        if(checkBox(view.boxType)) view.boxType.setSelectedIndex(0);
        if(checkBox(view.boxStore)) view.boxStore.setSelectedIndex(0);
        if(checkBox(view.boxProduct)) view.boxProduct.setSelectedIndex(0);
        if(checkBox(view.boxProductType)) view.boxProductType.setSelectedIndex(0);
        if(checkBox(view.boxTitleFont)) view.boxTitleFont.setSelectedIndex(0);
        if(checkBox(view.boxLabelFont)) view.boxLabelFont.setSelectedIndex(0);
        if(checkBox(view.boxTextFont)) view.boxTextFont.setSelectedIndex(0);
        if(checkBox(view.boxBtnFont)) view.boxBtnFont.setSelectedIndex(0);
        if(checkBox(view.boxMenuFont)) view.boxMenuFont.setSelectedIndex(0);
        if(checkBox(view.boxMenuItemFont)) view.boxMenuItemFont.setSelectedIndex(0);
        if(checkBox(view.boxTableFont)) view.boxTableFont.setSelectedIndex(0);
        if(checkBox(view.boxTblHeadFont)) view.boxTblHeadFont.setSelectedIndex(0);
        if(checkBox(view.boxTitleFormat)) view.boxTitleFormat.setSelectedIndex(0);
        if(checkBox(view.boxLabelFormat)) view.boxLabelFormat.setSelectedIndex(0);
        if(checkBox(view.boxTextFormat)) view.boxTextFormat.setSelectedIndex(0);
        if(checkBox(view.boxBtnFormat)) view.boxBtnFormat.setSelectedIndex(0);
        if(checkBox(view.boxMenuFormat)) view.boxMenuFormat.setSelectedIndex(0);
        if(checkBox(view.boxMenuItemFormat)) view.boxMenuItemFormat.setSelectedIndex(0);
        if(checkBox(view.boxTableFormat)) view.boxTableFormat.setSelectedIndex(0);
        if(checkBox(view.boxTblHeadFormat)) view.boxTblHeadFormat.setSelectedIndex(0);

        view.lblChangedPrice.setText("");

        view.tblStore.removeAll();
        view.tblProduct.removeAll();
        view.tblSelect.removeAll();
        view.tblStore.removeAll();
    }

    public int selectColor(StoreView view){
        Color initialcolor = Color.RED;

        Color color = JColorChooser.showDialog(view.frame, "Select a color", initialcolor);

        return color.getRGB();
    }

    public int textColor(int rgb){
        Color color = new Color(rgb);
        double brightness = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
        if(brightness < 128){
            return -1;
        }
        return -15856114;
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

    private boolean checkBox(JComboBox comboBox){
        return comboBox.getItemCount() > 0;
    }
}
