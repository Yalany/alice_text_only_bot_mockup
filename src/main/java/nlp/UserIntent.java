package nlp;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class UserIntent {
    @SerializedName("synonyms")
    private final HashSet<String> synonyms = new HashSet<>();
    /**
     * @param synonymsCollection коллекция String с синонимами, добавляемыми к списку синонимов этого интента
     */
    public UserIntent addSynonyms(final Collection<? extends String> synonymsCollection) {
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
        // проверяем каждое слово во вводе пользователя на предмет совпадения или похожести на синонимы интента
        return Arrays.stream(words).anyMatch(this::isIntended);
    }

    /**
     * @param word слово, котороя будет проверено на принадлежность к этому интенту
     * @return true если хотя бы один записанный синоним похож на word
     *         false если ни один записанный синоним не похож на word
     */
    private boolean isIntended(final String word) {
        // сначала проверяем прямое совпадение по списку синонимов, потом проверяем каждый из синонимов на похожесть
        return synonyms.contains(word) || synonyms.stream().anyMatch(synonym -> StringSimilarity.isSimilar(synonym, word));
    }
}
