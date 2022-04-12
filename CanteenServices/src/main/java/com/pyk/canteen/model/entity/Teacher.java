package com.pyk.canteen.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pyk.canteen.fieldcheck.annotation.*;
import com.pyk.canteen.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_teacher")
public class Teacher implements FieldCheckInterface<Teacher> {
    @TableId
    @StringLengthMax(msg = "用户名最大长度不能超过${value}", value = 32)
    @StringLengthMin(msg = "用户名不能小于${value}位", value = 4)
    @StringRegex(msg = "用户名只能由数字字母和_.@符号组成", value = "[a-zA-Z0-9]{4,32}")
    private String uid;

    /*** 学生姓名 */
    @StringLengthMax(msg = "教师姓名不能超过${value}个字符", value = 10)
    @StringNonNull("教师姓名不能为空")
    private String name;

    /*** 性别:0未知,1男,2女 */
    @NumberEnum(msg = "性别数值错误", value = {0, 1, 2})
    private Integer gender;

    /*** 电话号码 */
    @StringLengthMax(msg = "电话不能大于${value}位", value = 11)
//    @StringRegex(value = "[0-9]{11}")
    private String phone;

    /*** 个人描述 */
    @StringLengthMax(msg = "描述不能超过${value}个字符", value = 300)
    private String des;
}
