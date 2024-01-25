public class GenericExample {
  
  public static void main(String... args) {
    String str = A.<String>choose("1", "2");
    Integer integer = A.choose(1, 2);
  }
  
  private static class A {
    static <T> T choose(T v1, T v2) {
      return v1;
    }
  }
}
