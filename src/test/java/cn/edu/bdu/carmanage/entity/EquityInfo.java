package cn.edu.bdu.carmanage.entity;

import java.util.Date;

/**
 * @author: Winston
 * @createTime: 2020/8/4
 */
public class EquityInfo {


    private static final long serialVersionUID = 8000233216315537409L;

    private Long id;

    /**
     * 客户号
     */
    private String customerNumber;

    /**
     * 活动号
     */
    private String activityNumber;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 用户手机号
     */
    private String customerMobile;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 券码
     */
    private String couponCode;

    /**
     * 券码开始使用时间
     */
    private Date useStartTime;

    /**
     * 券码使用截止时间
     */
    private Date useEndTime;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 是否重复：0
     */
    private Integer isRepeat;

    /**
     * 数据是否有效：0 无效 1 有效
     */

    private Integer dataStatus;

    /**
     * 是否上传
     */
    private Integer isUpload;

    /**
     * 上传描述信息
     */
    private String uploadInfo;

    /**
     * 首刷日期
     */
    private Date firstDate;

    /**
     * 备注
     */
    private String comment;

    /**
     * 批次信息
     */
    private String batch;

    /**
     * 创建人
     */
    private String createOper;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private String updateOper;

    /**
     * 更新时间
     */
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }


    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }


    public Integer getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Integer isRepeat) {
        this.isRepeat = isRepeat;
    }


    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }


    public String getUploadInfo() {
        return uploadInfo;
    }

    public void setUploadInfo(String uploadInfo) {
        this.uploadInfo = uploadInfo;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }


    public String getCreateOper() {
        return createOper;
    }

    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateOper() {
        return updateOper;
    }

    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
