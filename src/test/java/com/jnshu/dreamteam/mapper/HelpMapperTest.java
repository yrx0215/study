package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.Mission;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.server.SourceFolderUrlFilter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author draper_hxy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelpMapperTest {

    @Autowired
    MissionMapper missionMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Test
    public void test(){
        Mission mission = new Mission();


        mission.setSubjectId(1L);
        mission.setCourseId(1L);
        mission.setLessonId(1L);
        mission.setMissionName("test");
        mission.setCreateAt(System.currentTimeMillis());
        missionMapper.addMission(mission);
}

@Test
    public void test1(){
//    Subject subject = new Subject();
//    subject.setSubjectStatus(1);
    MyPage myPage = new MyPage(1,10);

    System.out.println(subjectMapper.selectSubjectStatusOrName(myPage,1,null));
}
    @Test
    public void sdklfj(){
        int i = 1;
        System.out.println(i++ + ++i);
        Student student = new Student();
        System.out.println(EmptyUtil.isEmpty(student));
    }


}
