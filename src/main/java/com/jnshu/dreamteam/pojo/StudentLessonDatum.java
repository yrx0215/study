package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class StudentLessonDatum implements Serializable {
	@TableField("studnet_id")
	private Long studentId;

	@TableField("class_id")
	private Long classId;

	@TableField("buy")
	private Integer buy;

	@TableField("enshrine")
	private Integer enshrine;

	@TableField("datum")
	private Integer datum;

	@TableField("lesson_status")
	private Integer lessonStatus;
}
