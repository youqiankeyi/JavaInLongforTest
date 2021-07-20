package com.excel.demo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
@RestController
@RequestMapping("/ebl/test")
@RunWith(SpringRunner.class)
@Slf4j
public class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testExport() throws IOException {
		String path = "E:\\工作\\2021\\7月\\sign.xlsx";

		MockMultipartFile multipartFile = new MockMultipartFile("sign", "sign.xlsx", ContentType.APPLICATION_FORM_URLENCODED.toString(), new FileInputStream(path));
		importCustomerBySign(multipartFile);
	}

	private void importCustomerBySign(MultipartFile multipartFile) {
		//校验文件类型
		ExcelImportResult excelImportResult = excelTypeVerify(multipartFile);
		if (excelImportResult != null) {
			if (excelImportResult.isVerifyFail()) {
				//有错误数据
				//整理错误信息并返回
				verifyFailParse(excelImportResult);
			}else {
				//导入数据
				List list = excelImportResult.getList();
				if(CollectionUtils.isNotEmpty(list)){

				}
			}
		}

	}

	private ExcelImportResult excelTypeVerify(MultipartFile multipartFile) {
		String originalFilename = multipartFile.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
		if (!(suffix.equals(".xlsx") || suffix.equals(".xls"))) {
			checkArgumentByRt(false, "文件格式错误");
		}
		InputStream inputStream = null;
		try {
			inputStream = multipartFile.getInputStream();
		} catch (IOException e) {
			checkArgumentByRt(false, e.getMessage());
		}
		ImportParams params = new ImportParams();
		params.setNeedVerify(true);
		params.setHeadRows(1);
		ExcelImportResult excelImportResult = null;
		try {
			excelImportResult = ExcelImportUtil.importExcelMore(inputStream, ActivityCustomerBySignImportModel.class, params);
		} catch (Exception e) {
			checkArgumentByRt(false, e.getMessage());
		}
		return excelImportResult;
	}

	private void verifyFailParse(ExcelImportResult excelImportResult) {
		StringBuilder msg = new StringBuilder();
		List failList = excelImportResult.getFailList();
		if (CollectionUtils.isNotEmpty(failList)) {
			failList.forEach(a -> {
				ActivityCustomerBySignImportModel activityCustomerBySignImportModel = (ActivityCustomerBySignImportModel) a;
				msg.append("第" + (activityCustomerBySignImportModel.getRowNum()+1) + "行的错误是：" + activityCustomerBySignImportModel.getErrorMsg()+"。");
			});

			checkArgumentByRt(false, msg.toString());
		}
	}

	public static void checkArgumentByRt(boolean expression, @Nullable String errorMessage) {
		if (!expression) {
			throw new RuntimeException(errorMessage);
		}
	}

}
