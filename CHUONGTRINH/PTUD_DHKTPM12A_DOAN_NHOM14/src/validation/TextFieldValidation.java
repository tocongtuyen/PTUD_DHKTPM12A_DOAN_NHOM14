package validation;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TextFieldValidation {
    public static boolean isTextFieldNotEmpty(TextField tf){
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }

    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNotEmpty(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean isTextFieldNotEmpty(TextArea tf){
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }

    public static boolean isTextFieldNotEmpty(TextArea tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNotEmpty(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorMatKhau(PasswordField tf){
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }

    public static boolean errorMatKhau(PasswordField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorMatKhau(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorTaiKhoan(TextField tf){
        boolean b = false;
        if (tf.getText().matches("[A-Za-z0-9]{6,20}"))
            b = true;
        return b;
    }

    public static boolean errorTaiKhoan(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorTaiKhoan(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean isTextFieldTypeNumber(TextField tf){
        boolean b = false;
        if (tf.getText().matches("[0-9]+"))
            b = true;
        return b;
    }

    public static boolean isTextFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldTypeNumber(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorSoDienThoai(TextField tf){
        boolean b = false;
        if (tf.getText().matches("(\\+84|0)\\d{9,10}"))
            b = true;
        return b;
    }

    public static boolean errorSoDienThoai(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorSoDienThoai(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorEmail(TextField tf){
        boolean b = false;
        if (tf.getText().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$"))
            b = true;
        return b;
    }

    public static boolean errorEmail(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorEmail(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorTen(TextField tf){
        boolean b = false;
        if (tf.getText().matches("[^0-9]+"))
            b = true;
        return b;
    }

    public static boolean errorTen(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorTen(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorSoLuongTon(TextField tf){
        boolean b = false;
        Double i = 0.0;
        try {
            i = Double.valueOf(tf.getText());
        }catch (Exception e){

        }
        if (i < 500 && i > 0)
            b = true;
        return b;
    }

    public static boolean errorSoLuongTon(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorSoLuongTon(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }

    public static boolean errorDonGia(TextField tf){
        boolean b = false;
        Double i = 0.0;
        try {
            i = Double.valueOf(tf.getText());
        }catch (Exception e){

        }
        if (i < 50000 && i >= 10)
            b = true;
        return b;
    }

    public static boolean errorDonGia(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!errorDonGia(tf)){
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return  b;
    }
}