import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.neperix.hobnob.posting.Article;
import com.neperix.hobnob.posting.ArticleService;
import com.neperix.hobnob.posting.ArticleServiceInProcess;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author petarmitrovic
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceInProcessTest {

    @InjectMocks
    private ArticleServiceInProcess service;

    @Mock
    private ArticleService articleService;

    @Test
    public void itShouldSubmitTheArticleToUnderlyingService() {

        String someTitle = "Some title";
        String someText = "Some text";
        long authorId = 1L;

        service.submit(someTitle, someText, authorId);

        Mockito.verify(articleService, times(1)).submitArticle(someTitle, someText, authorId);
    }

    @Test
    public void itShouldReturnListOfArticlesAsReturnedFromUnderlyingService() {
        String title1 = "title 1", text1 = "text 1", title2 = "title 2", text2 = "text 2";
        long author1 = 1L, author2 = 2L;
        when(articleService.list()).thenReturn(
                Arrays.asList(new Article(title1, text1, author1), new Article(title2, text2, author2)));

        List<com.neperix.hobnob.posting.api.Article> articles = service.list();

        assertThat(articles, Matchers.hasSize(2));

        assertThat(articles.get(0).getTitle(), equalTo(title1));
        assertThat(articles.get(0).getText(), equalTo(text1));
        assertThat(articles.get(0).getAuthorId(), equalTo(author1));

        assertThat(articles.get(1).getTitle(), equalTo(title2));
        assertThat(articles.get(1).getText(), equalTo(text2));
        assertThat(articles.get(1).getAuthorId(), equalTo(author2));
    }
}
