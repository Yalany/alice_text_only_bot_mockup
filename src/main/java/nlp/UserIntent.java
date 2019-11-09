package nlp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class UserIntent {
    private final HashSet<String> synonyms = new HashSet<>();
    /**
     * @param synonymsCollection коллекция String с синонимами, добавляемыми к списку синонимов этого интента
     */
    public void addSynonyms(Collection<? extends String> synonymsCollection) {
        synonyms.addAll(synonymsCollection);
    }

    /**
     * @param synonymsArray массив String с синонимами, добавляемыми к списку синонимов этого интента
     */
    public void addSynonyms(String[] synonymsArray) {
        synonyms.addAll(Arrays.asList(synonymsArray));
    }

    /**
     * @param synonym синоним, добавляемый к списку синонимов этого интента
     */
    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

    /**
     * @param words массив слов, каждое из которых будет проверена на принадлежность к этому интенту
     * @return true если хотя бы один записанный синоним похож хотя бы на одно слово из words
     *         false если ни один записанный синоним не похож ни на одно слово из words
     */
    public boolean isIntended(String[] words) {
        // проверяем каждое слово во вводе пользователя на предмет совпадения или похожести на синонимы интента
        return Arrays.stream(words).anyMatch(this::isIntended);
    }

    /**
     * @param word слово, котороя будет проверено на принадлежность к этому интенту
     * @return true если хотя бы один записанный синоним похож на word
     *         false если ни один записанный синоним не похож на word
     */
    private boolean isIntended(String word) {
        // сначала проверяем прямое совпадение по списку синонимов, потом проверяем каждый из синонимов на похожесть
        return synonyms.contains(word) || synonyms.stream().anyMatch(synonym -> StringSimilarity.isSimilar(synonym, word));
    }
}
