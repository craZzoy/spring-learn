package contorllertest;

import com.controller.SpittleController;
import com.vo.NewSpittle;
import com.vo.Spittle;
import com.dao.inte.SpittleRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by zwz on 2019/5/16.
 */
public class SpittleTest {

    /**
     * 测试返回spittles
     *
     * @throws Exception
     */

    public void shouldShowRecentSpittles() throws Exception {
        List<Spittle> spittles = createSpittles(20);

        //模拟repository对象
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittle(Long.MAX_VALUE, 20)).thenReturn(spittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")) //手动设置视图，以免与请求地址混淆
                .build();

        /**
         * 模拟发起请求
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                        CoreMatchers.hasItems(spittles.toArray())));

    }

    private List<Spittle> createSpittles(int size) {
        List<Spittle> spittles = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            spittles.add(new Spittle("Spittle" + j, new Date()));
        }
        return spittles;
    }

    /**
     * 测试跳转到注册页
     */
    //@Test
    public void shouldShowRegistration() throws Exception {
        SpittleController controller = new SpittleController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/register"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));

    }

    /**
     * 测试注册
     */
    @Test
    public void shouldProcessRegistration() throws Exception {
        SpittleRepository repository = Mockito.mock(SpittleRepository.class);

        //模拟数据查询层
        NewSpittle save = new NewSpittle("root", "root", "Jack", "Bauer");
        NewSpittle unsave = new NewSpittle(24L, "root", "root", "Jack", "Bauer");
        Mockito.when(repository.save(unsave)).thenReturn(save);

        SpittleController controller = new SpittleController(repository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/spittles/register")
                .param("firstname", "Jack")
                .param("lastname", "Bauer")
                .param("username", "root")
                .param("password", "root"))
                .andExpect(MockMvcResultMatchers.redirectedUrl(("/spittles/root")));

        //校验保存情况
        Mockito.verify(repository, atLeastOnce()).save(unsave);

    }
}
