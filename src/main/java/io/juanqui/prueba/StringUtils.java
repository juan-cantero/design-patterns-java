package io.juanqui.prueba;

public class StringUtils {

    public static String extractSubstringAfterLastDelimiter(String input, String delimiter, int delimiterPositionFromEnd) {
      if (input ==null || delimiter == null) {
          throw new IllegalArgumentException("Input and delimiter cannot be null");
      }

      int lastIndex = input.length();
      for (int i = 0 ; i < delimiterPositionFromEnd; i++) {
          lastIndex = input.lastIndexOf(delimiter, lastIndex - 1);
          if (lastIndex == -1) {
              return "";
          }
      }

      return input.substring(lastIndex + delimiter.length());
    }

    public static void main(String[] args) {
        String input = "lalal/123/hello";
        String delimiter = "/";
        int startIndex = 2;
        String extractedString = extractSubstringAfterLastDelimiter(input, delimiter, startIndex);
        System.out.println("Extracted String: " + extractedString);

        String input2 = "a/1/h";
        System.out.println(input2.length());
        int lastIndex = input2.lastIndexOf(delimiter, input2.length() - 3);
        System.out.println(lastIndex);
        String extractedString2 = input2.substring(lastIndex + delimiter.length());
        System.out.println(extractedString2);
    }
}
