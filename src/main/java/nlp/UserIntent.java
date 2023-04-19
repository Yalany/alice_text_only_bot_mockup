package nlp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class UserIntent {
  private final HashSet<String> synonyms = new HashSet<>();
  /**
   * @param synonymsCollection коллекция String с синонимами, добавляемыми к списку синонимов этого интента
   */
  public UserIntent addSynonyms(final Collection<String> synonymsCollection) {
    synonyms.addAll(synonymsCollection);
    return this;
  }

  /**
   * @param synonymsArray массив String с синонимами, добавляемыми к списку синонимов этого интента
   */
  public UserIntent addSynonyms(final String[] synonymsArray) {
    synonyms.addAll(Arrays.asList(synonymsArray));
    return this;
  }

  /**
   * @param synonym синоним, добавляемый к списку синонимов этого интента
   */
  public UserIntent addSynonym(final String synonym) {
    synonyms.add(synonym);
    return this;
  }

  /**
   * @param words массив слов, каждое из которых будет проверена на принадлежность к этому интенту
   * @return true если хотя бы один записанный синоним похож хотя бы на одно слово из words
   *         false если ни один записанный синоним не похож ни на одно слово из words
   */
  public boolean isIntended(final String[] words) {
    return Arrays.stream(words).anyMatch(this::isIntended);
  }

  /**
   * @param words коллекция слов, каждое из которых будет проверено на принадлежность к этому интенту
   * @return true если хотя бы один записанный синоним похож хотя бы на одно слово из words
   *         false если ни один записанный синоним не похож ни на одно слово из words
   */
  public boolean isIntended(final Collection<String> words) {
    return words.stream().anyMatch(this::isIntended);
  }

  /**
   * @param word слово, которое будет проверено на принадлежность к этому интенту
   * @return true если хотя бы один записанный синоним похож на word
   *         false если ни один записанный синоним не похож на word
   */
  public boolean isIntended(final String word) {
    return synonyms.contains(word) || synonyms.stream().anyMatch(synonym -> StringSimilarity.isSimilar(synonym, word));
  }
}
