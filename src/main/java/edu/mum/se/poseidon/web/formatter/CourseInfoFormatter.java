package edu.mum.se.poseidon.web.formatter;

import edu.mum.se.poseidon.web.models.CourseInfo;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Yuriy Yugay on 10/18/2017.
 *
 * @author Yuriy Yugay
 */
@Component
public class CourseInfoFormatter
        implements Formatter<CourseInfo> {
    @Override
    public CourseInfo parse(String s, Locale locale) throws ParseException {
        System.out.println("Parse: " + s);
        CourseInfo info = new CourseInfo();
        info.setId(Long.valueOf(s));
        return info;
    }

    @Override
    public String print(CourseInfo courseInfo, Locale locale) {
        return courseInfo.getName();
    }
}
