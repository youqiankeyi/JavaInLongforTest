package com.excel.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCustomerBySignImportModel implements IExcelModel, IExcelDataModel {

    @NotNull(message = "不能为空")
    @Excel(name = "客户姓名（必填）")
    @Size(max=20, message = "要小于20个字")
    private String customerName;

    @NotNull(message = "不能为空")
    @Excel(name = "手机号（必填）")
    @Pattern(regexp ="^1\\d{10}$", message = "手机号格式错误")
    private String customerPhone;


    @NotNull(message = "不能为空")
    @Excel(name = "签到日期（必填）")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private String signDate;


    private Integer rowNum;

    private String errorMsg;
}

