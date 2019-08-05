import com.drivingtalking.dao.MemberDAO;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.util.PagerManager;
import com.drivingtalking.util.PagerSupporter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private MemberDAO userDAO;

    @Test
    public void save(){
        Member member  = new Member();
        member.setId("1");
        member.setNickName("wsk7860");
        member.setMobile("18666982982");
        member.setLoginName("粤CW9170");
        userDAO.save(member);
    }

    @Test
    public void findById(){
        Map<String,Object> params = new HashMap<>();
        params.put("id","1");
        PagerSupporter supporter = new PagerSupporter(1,10,null);
        List<Member> memberList = PagerManager.paging(supporter,() ->userDAO.findByParams(Member.class,params));
        Assert.assertNotNull(memberList);
        System.out.println(PagerManager.getPagerSupport().getTotal());;
    }

}
