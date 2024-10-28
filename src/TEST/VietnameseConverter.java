package TEST;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class VietnameseConverter {
    
    public static String removeAccents(String text) {
        // Chuẩn hóa chuỗi, chuyển các ký tự có dấu về dạng cơ bản
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        
        // Sử dụng biểu thức chính quy để loại bỏ các ký tự dấu
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String noAccentText = pattern.matcher(normalizedText).replaceAll("");
        
        // Đảm bảo loại bỏ các ký tự Unicode không cần thiết
        noAccentText = noAccentText.replaceAll("đ", "d");
        noAccentText = noAccentText.replaceAll("Đ", "D");
        
        return noAccentText;
    }
    
    public static void main(String[] args) {
        String originalText = "Văn Bá Phạm Tấn";
        String convertedText = removeAccents(originalText);
        
        System.out.println("Original text: " + originalText);
        System.out.println("Converted text: " + convertedText);
    }
}

