package top.coderyjc.certificate.model.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import lombok.Setter;
import top.coderyjc.certificate.model.entity.WrittenScore;

import javax.management.Query;

/**
 * ClassName: WrittenScoreFilter
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/5/2023 12:46 PM
 * @Version 1.0
 */


@Getter
@Setter
public class WrittenScoreDTO {

    private Integer current;
    private Integer size;
    private String name;
    private String gender;
    private String examId;
    private String identificationId;
    private String workAddress;
    private String examDate;
    private String educationStatus;
    private String educationPsychologyStatus;
    private String professionalEthicStatus;
    private String educationSort;
    private String educationPsychologySort;
    private String professionalEthicSort;

    public QueryWrapper<WrittenScore> generateQueryWrapper(){
        QueryWrapper<WrittenScore> wrapper = new QueryWrapper<>();
//        if(!gender.equals("")) wrapper.eq("sex", gender);
        if(!name.equals("")) wrapper.eq("name", name);
        if(!examId.equals("")) wrapper.eq("exam_id", examId);
        if(!identificationId.equals("")) wrapper.eq("identification_id", identificationId);
        if(!educationStatus.equals("")) wrapper.eq("education_status", educationStatus);
        if(!educationPsychologyStatus.equals("")) wrapper.eq("education_psychology_status", educationPsychologyStatus);
        if(!professionalEthicStatus.equals("")) wrapper.eq("professional_ethic_status", professionalEthicStatus);
        if(!workAddress.equals("")) wrapper.eq("work_address", workAddress);
        if(!examDate.equals("")) wrapper.eq("exam_date", examDate);
        return wrapper;
    }
//
//    public void setCurrent(Integer current) {
//        this.current = current;
//    }
//
//    public void setSize(Integer size) {
//        this.size = size;
//    }
//
//    public void setGender(String gender){
//        this.gender = gender.equals("") ? "" : gender.trim();
//    }
//    public void setName(String name){
//        this.name = name.equals("") ? "" : name.trim();
//    }
//    public void setExamId(String examId){
//        this.examId = examId.equals("") ? "" : examId.trim();
//    }
//    public void setIdentificationId(String identificationId){
//        this.identificationId = identificationId.equals("") ? "" : identificationId.trim();
//    }
//    public void setEducationStatus(String educationStatus){
//        this.educationStatus = educationStatus.equals("") ? "" : educationStatus.trim();
//    }
//    public void setEducationPsychologyStatus(String educationPsychologyStatus){
//        this.educationPsychologyStatus = educationPsychologyStatus.equals("") ? "" : educationPsychologyStatus.trim();
//    }
//    public void setProfessionalEthicStatus(String professionalEthicStatus){
//        this.professionalEthicStatus = professionalEthicStatus.equals("") ? "" : professionalEthicStatus.trim();
//    }
//    public void setEducationSort(String educationSort){
//        this.educationSort = educationSort.equals("") ? "" : educationSort.trim();
//    }
//    public void setEducationPsychologySort(String educationPsychologySort){
//        this.educationPsychologySort = educationPsychologySort.equals("") ? "" : educationPsychologySort.trim();
//    }
//    public void setProfessionalEthicSort(String professionalEthicSort){
//        this.professionalEthicSort = professionalEthicSort.equals("") ? "" : professionalEthicSort;
//    }
//    public void setWorkAddress(String workAddress){
//        this.workAddress = workAddress.equals("") ? "" : workAddress;
//    }
//    public void setExamDate(String examDate){
//        this.examDate = examDate.equals("") ? "" : examDate;
//    }

    @Override
    public String toString() {
        return "WrittenScoreDTO{" +
                "current=" + current +
                ", size=" + size +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", examId='" + examId + '\'' +
                ", identificationId='" + identificationId + '\'' +
                ", educationStatus='" + educationStatus + '\'' +
                ", educationPsychologyStatus='" + educationPsychologyStatus + '\'' +
                ", professionalEthicStatus='" + professionalEthicStatus + '\'' +
                ", educationSort='" + educationSort + '\'' +
                ", educationPsychologySort='" + educationPsychologySort + '\'' +
                ", professionalEthicSort='" + professionalEthicSort + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", examDate='" + examDate + '\'' +
                '}';
    }
}
