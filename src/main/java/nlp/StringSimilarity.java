package nlp;

final class StringSimilarity {
  private StringSimilarity() { }

  // число тупо с потолка, надо тестить и тюнить
  private static final double SIMILARITY_REQUIRED = 0.8;

  /**
   * @param stringA первая сравниваемая строка
   * @param stringB вторая сравниваемая строка
   * @return похожи ли полученные строки между собой
   */
  static boolean isSimilar(final String stringA, final String stringB) {
    return getSimilarity(stringA, stringB) >= SIMILARITY_REQUIRED;
  }

  /**
   * @param stringA первая сравниваемая строка
   * @param stringB вторая сравниваемая строка
   * @return число [0.0, 1.0], выражающее степень пожожести строк, где
   * 0.0 - совсем не похожи
   * 1.0 - одинаковые
   */
  private static double getSimilarity(final String stringA, final String stringB) {
    String longer = stringA.toLowerCase();
    String shorter = stringB.toLowerCase();
    if (stringA.length() < stringB.length()) {
      longer = stringB;
      shorter = stringA;
    }
    int longerLength = longer.length();
    if (longerLength == 0) return 1.0;
    return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
  }

  // нихуя не понял и не слишком пытался, на тестовых данных показало себя хорошо
  // по ссылке ниже есть куча реализаций и оттуда можно почитать идею
  // Example implementation of the Levenshtein Edit Distance
  // See http://rosettacode.org/wiki/Levenshtein_distance#Java
  private static int editDistance(final String stringA, final String stringB) {
    var a = stringA.toLowerCase();
    var b = stringB.toLowerCase();
    var costs = new int[b.length() + 1];
    for (int i = 0; i <= a.length(); i++) {
      var lastValue = i;
      for (int j = 0; j <= b.length(); j++) {
        if (i != 0) {
          if (j > 0) {
            var newValue = costs[j - 1];
            if (a.charAt(i - 1) != b.charAt(j - 1))
              newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
            costs[j - 1] = lastValue;
            lastValue = newValue;
          }
        } else costs[j] = j;
      }
      if (i > 0) costs[b.length()] = lastValue;
    }
    return costs[b.length()];
  }
}
