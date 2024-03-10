package top.coderyjc.certificate.model.vo;

import lombok.Data;

/**
 * ClassName: ProvinceCountVO
 * Package: top.coderyjc.certificate.model.vo
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/22/2023 12:56 PM
 * @Version 1.0
 */
@Data
public class ProvinceCountVO {

    private String name;

    private Integer value;

}


//根据以下要求使用SQL语句创建视图：表中有身份证号，根据身份证号的前6位进行分组，给出分组结果和组内数据个数
