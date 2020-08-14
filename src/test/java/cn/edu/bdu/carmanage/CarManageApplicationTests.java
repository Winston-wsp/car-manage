package cn.edu.bdu.carmanage;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.sms.SmsModel;
import cn.edu.bdu.carmanage.listener.ExcelOptionsService;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.service.cms.sms.SendSmsService;
import cn.edu.bdu.carmanage.util.AESUtils;
import cn.edu.bdu.carmanage.util.ExcelTool;
import cn.edu.bdu.carmanage.util.RSAUtils;
import cn.edu.bdu.carmanage.utils.Md5;
import cn.hutool.core.date.*;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@SpringBootTest
class CarManageApplicationTests {

    @Autowired
    private CarParkingMapper carParkingMapper;
    @Autowired
    private ExcelOptionsService excelOptionsService;
    @Autowired
    private SendSmsService sendSmsService;

    @Test
    public void test01() {
        String password = "abc123";
        String s = Md5.encodeByMD5(password);
        System.out.println(s);
    }

    @Test
    public void test02() {
        List<CarParking> list = carParkingMapper.getMyCarParing("4d3e6c9cbec284d6c5bc1311923e68f0'");
        list.forEach(carParking -> {
            System.out.println(carParking);
        });
    }

    @Test
    public void test04() throws IOException {
        // FieldEntity是我用来接收Excel数据的对象
        List<FieldEntity> jsonList = new ArrayList<>();
        String path = "E://abc.xlsx";
        File file = new File("C:\\Users\\WU\\Desktop\\6a7ecfe2d66c424183911d2ae44ed75a (1).xlsx");
        // XSSFWorkbook只能读取.xlsx结尾的表格
        Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
        // 按索引获取
        Sheet sheet = workbook.getSheetAt(0);
        // 行索引row和列索引col都是以 0 起始
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            FieldEntity fieldEntity = new FieldEntity();
            Cell cell1 = sheet.getRow(i).getCell(0);
            Cell cell2 = sheet.getRow(i).getCell(1);
            Cell cell3 = sheet.getRow(i).getCell(2);
            Cell cell4 = sheet.getRow(i).getCell(3);
            Cell cell5 = sheet.getRow(i).getCell(4);
            Cell cell6 = sheet.getRow(i).getCell(5);
            Cell cell7 = sheet.getRow(i).getCell(6);
            jsonList.add(fieldEntity);

        }
        String jsonString = JSONArray.toJSONString(jsonList);
        System.out.println(jsonString);

    }

    @Test
    public void test05() throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(2);
        importParams.setHeadRows(1);
        //test03241703 (1)(1).xlsx
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");
        List<FieldEntity> list = ExcelImportUtil.importExcel(new FileInputStream(file), FieldEntity.class, importParams);
        System.out.println(list);
    }

    @Test
    public void test06() {
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");

        List<List<String>> result = new ArrayList<>();
        try {
            result = excelOptionsService.writeWithoutHead(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<FieldEntity> fieldEntityList = new ArrayList<>();
        result.forEach(list -> {
            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setMobile(list.get(0));
            fieldEntity.setFullName(list.get(1));
            fieldEntity.setUserAuthority(list.get(2));
            fieldEntity.setIdNum(list.get(3));
            fieldEntity.setUserGroup(list.get(4));
            fieldEntity.setUserAuthority(list.get(5));
            fieldEntity.setEmployeeNumber(list.get(6));

            fieldEntityList.add(fieldEntity);
        });

        System.out.println("读取结果：" + result);

    }

    @Test
    public void test6() {
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");

        ExcelTool excelTool = new ExcelTool();
        List<?> objects = excelTool.simpleRead("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx", User.class);
        System.out.println(objects);
    }

    @Test
    public void test7() {
        List<Map<String, String>> books = new ArrayList<>();
        Map<String, String> info = new HashMap<>();
        info.put("name", "骆驼祥子");
        info.put("price", "25.00");
        info.put("author", "老舍");

        Map<String, String> info1 = new HashMap<>();
        info1.put("name", "骆驼祥子1");
        info1.put("price", "25.00");
        info1.put("author", "老舍1");

        Map<String, String> info2 = new HashMap<>();
        info2.put("name", "骆驼祥子2");
        info2.put("price", "25.00");
        info2.put("author", "老舍2");

        Map<String, String> info3 = new HashMap<>();
        info3.put("name", "骆驼祥子3");
        info3.put("price", "25.00");
        info3.put("author", "老舍3");

        books.add(info);
        books.add(info1);
        books.add(info2);
        books.add(info3);

        List<String> name = books.stream().map(map -> {
            return map.get("name");
        }).collect(Collectors.toList());
        System.out.println(name);

    }

    @Test
    public void test8() {
        List<String> strList = new ArrayList<String>();
        strList.add("a");
        strList.add("b");
//        strList.add("c");
//        strList.add("d");
        List<String> strList2 = new ArrayList<String>();
//        strList2.add("b");
        strList2.add("c");
        strList2.add("d");
        strList2.add("e");

        strList.retainAll(strList2);
        System.out.println(strList);

    }

    @Test
    public void test9() {
        DateTime parse = DateUtil.parse("2020-04-01");
        Timestamp timestamp = parse.toTimestamp();

        DateTime date1 = DateUtil.date(1587475023538L);
        DateTime date2 = DateUtil.date(1611235023000L);
        String name = date1.monthEnum().name();
        int year = date2.year() - date1.year();
        int month = date2.month() - date1.month();
        int result = Math.abs(month + year * 12);
        DateTime dateTime = DateUtil.offsetMonth(date1, 1);
        System.out.println();
    }

    @Test
    public void test10() {
        DateTime start = DateUtil.date();
        DateTime end = DateUtil.offsetDay(start, -1);

        String fromDate = DateUtil.format(start, "yyyy/MM/dd");
        String toDate = DateUtil.format(end, "yyyy/MM/dd");
        System.out.println();
    }

    @Test
    public void test11() {
        DateTime date = DateUtil.date(1121321312);
        DateUtil.format(date, "yyyy-MM-dd");
        System.out.println();

    }

    @Test
    public void test12() {
        String account = "6217000140012931001";
        String s = hideData(account, 4, 4);
        System.out.println(s);

    }

    /**
     * 对重要数据进行隐藏处理
     *
     * @param data         //要隐藏的数据
     * @param beforeLength //前面保留位数
     * @param afterLength  //后面保留位数
     * @return
     */
    public static String hideData(String data, int beforeLength, int afterLength) {
        if (StringUtils.isBlank(data)) {
            return data;
        }
        int length = data.length();
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i >= length - afterLength || i < beforeLength) {
                sb.append(data.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }
        return sb.toString();
    }

    @Test
    public void test13() {
        SmsModel smsModel = new SmsModel();
        smsModel.setMobile("17749707027");
        smsModel.setTemplateCode("您的验证码为：${code}，该验证码5分钟内有效，请勿泄漏于他人！");
        smsModel.setTemplateParam("135566");
        smsModel.setTemplateParam(null);
//        sendSmsService.send(smsModel);
    }

    @Test
    public void test14() {
        String id = "https://www.bilibili.com/video/BV15741177Eh?p=";

        for (int i = 1; i < 232; i++) {
            System.out.println(id + i);
        }
    }

    @Test
    public void test15() {
        String dateStr = "2020-06-04 08:34:23";
        DateTime dateTime = DateUtil.parse(dateStr);
//        DateTime beginOfWeek = DateUtil.beginOfWeek(dateTime);
//        DateTime offsetWeek = DateUtil.offsetWeek(beginOfWeek, -1);
//        System.out.println(offsetWeek);
        canSignIn(dateTime);

    }

    @Test
    public void test16() {
        Random rd = new Random();
        System.out.println(rd.nextInt(13));
    }

    /**
     * 判断是否能够补签
     * 周一早上10点前禁止补签上周及上周以前的签到
     *
     * @param date
     * @return
     */
    public static boolean canSignIn(Date date) {
        // 获取当前时间
        DateTime timeNow = new DateTime();
        timeNow = DateUtil.parse("2020-06-01 11:00:00");
        // 获取需要对比的时间
        DateTime dateTime = new DateTime(date);
        // 获取周一
        DateTime monday = DateUtil.beginOfWeek(timeNow);
        // 获取本周一10点的时间
        DateTime offsetTime = DateUtil.beginOfWeek(timeNow).offset(DateField.HOUR, 10);
        // 本周一10点的时间和需要对比时间的时间差
        long timeDiff = DateUtil.between(offsetTime, dateTime, DateUnit.SECOND, false);
        // 当前时间和周一10点的时间差
        long nowDiff = DateUtil.between(offsetTime, timeNow, DateUnit.SECOND, false);
        // 如果需要对比的时间在本周一10点之后则能被补签
        if (timeDiff > 0) {
            return true;
        }
        // 如果当前时间在周一10点之前且对比时间小于一周则能被补签
        if (nowDiff < 0) {
            // 本周一 一周前的时间
            DateTime lastWeek = DateUtil.offsetWeek(monday, -1);
            // 判断对比时间是否小于一周
            boolean isExpired = DateUtil.isExpired(lastWeek, offsetTime, dateTime);

            return !isExpired;
        }

        // 如果当前时间在周一10点后且补签的不是本周的数据则能不能被允许
        if (nowDiff > 0) {
            boolean isThisWeek = DateUtil.isExpired(monday, timeNow, date);
            return !isThisWeek;
        }

        return true;
    }

    @Test
    public void test17() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);

        Date time = calendar.getTime();
    }

    @Test
    public void test18() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-07-15 11:00:00");
        boolean b = canScheduleWork(date);
        System.out.println(b);
    }


    /**
     * 每周四中午12点前截止操作本周的排班计划维护
     *
     * @param date
     * @return
     */
    public static boolean canScheduleWork(Date date) {
        // 获取当前时间
        DateTime timeNow = new DateTime();
        timeNow = DateUtil.parse("2020-07-14 15:00:00");
        // 获取需要对比的时间
        DateTime dateTime = new DateTime(date);
        // 获取本周一和周日
        DateTime monday = DateUtil.beginOfWeek(timeNow);
        DateTime sunday = DateUtil.endOfWeek(timeNow);
        // 获取本周四12点的时间
        DateTime offsetTime = DateUtil.beginOfWeek(timeNow).offset(DateField.HOUR, 84);
        // 当前时间和周四12点的时间差
        long nowDiff = DateUtil.between(offsetTime, timeNow, DateUnit.SECOND, false);
        // 如果当前时间在本周四12点以后，则不允许导入本周排班了
        if (nowDiff > 0) {
            // 如果在本周一到周日则不允许排班
            if (!DateUtil.isExpired(monday, sunday, dateTime)) {
                return false;
            }
            // 本周一以前的排班也不允许
            if (dateTime.isBefore(monday)) {
                return false;
            }
        }
        // 如果当前时间在本周四12点之前
        //（1）需要导入的排班时间必须是今天以后的排班
        if (!dateTime.isAfter(timeNow)) {
            return false;
        }


        return true;
    }

    @Test
    public void test19() {
        Date date = new Date();
        String strDate = DateUtil.formatDate(date);

        DateTime dateTime = DateUtil.parse(strDate, "yyyy-MM-dd");
        System.out.println(dateTime);
    }

    @Test
    public void test20() {

        String time = "9d:00-18:00";
        String describe = "";
        String[] timeSplit = time.split("-");
        try {
            DateTime parse = DateUtil.parse(timeSplit[0]);
        } catch (DateException exception) {
            describe = "时间非法";
        }
        System.out.println(describe);
    }


    @Test
    public void test21() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    System.out.println("线程名：" + Thread.currentThread().getName() + "序号");

                }
            };
            executorService.execute(runnable);
        }
    }

    @Test
    public void test22() throws IOException {
        File file = new File("H:\\Wu\\IDEA Workspce2\\graduation\\car-manage\\src\\test\\java\\cn\\edu\\bdu\\carmanage\\ShopPlan.xls");
        InputStream inputStream = new FileInputStream(file);
        byte[] fileData = IOUtils.toByteArray(inputStream);

        File outFile = new File("D:\\abc.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);

        byte[] size = new byte[1024];
        fileOutputStream.write(fileData, 0, fileData.length);
        fileOutputStream.close();

        inputStream.close();

    }

    @Test
    public void test23() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");
        changeList(list1);
        System.out.println(list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);
    }

    private void changeList(List<String> list1) {
        list1.add("new");
    }

    @Test
    public void test24() throws IOException {
//        FileTransformObjectUtils.fileTransformObject("abc", EquityInfo.class);
        // 读入TXT文件
        File filename = new File("C:\\Users\\WU\\Desktop\\2020-08-11_奥维思测试.txt");
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(fis, "GBK"); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    @Test
    public void test25() throws Exception {
        String privateKey =   "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIpqLi4/NCM5A8CWZkxSYznHgqIEaNZlHCQAnOFoeZz5IVaEEWHAQSqQtqsGtD7Yd8ettqqDGbcX7uqA8DHSCwEEK2v7ybBwqljsdM76hBjufSxRrEZIMUg+4qW0phOlEWTNCZ7uD3I14qpRlTWyb6dBvNDHeVFReMJ04v0ztHx/AgMBAAECgYEAhD3J/VlZYMWaNif7eLPGJW/d+RkpaJyVdDg5qon8luLW3Se0y6W5HQfB3ki7cMe0OMxZFzPGH83374hc89xvsZBp4w6J7al8NOuL8cW3kaNx2VwUzxpqjMcNXimV+wrCYCrzFiNJr2gVFM0ezmMJmmRei17qlSu3jL6xb+hbx1ECQQDVFw5Yzly5/p/vwyAjob8Rbm9wlQ182VnWu9cD1vY+LBXODN+nuLBW2gYdrXgogSGPohxTO1aERh93ikLlyPztAkEApkmPU7m0f/ukzpq/wECxVGNLM6M/5SsK2S7OywYxcOHg6zwIije0wZHZaxw/WcTMArXPFLOESjKs+Q2JeL6dmwJASMVH7AIFXqtbGTlMx35cTuqk+hCc+48KhC+/3RrTGyZZB/YThyamafKo9gegqkx3FqMpAy+XezKrWlqIQArkfQJAKBWXXUFox2krwzn+eyOdG5CL8jiqEF4d0Vi9NKebh3CLvWjNTjQ7eGXmGTHH+6W63/RfpMfZXeF1XNvshZS9fQJBALTqFA12LfXTI9LqPupfBbJEZ4Hreblfjd31aecMEK82LOPX4BCNzCfvFcSQtg21n0wgD8mO08nAtY5jrmB/wsw=";

        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        //String mfrchant_id="fffff";
        String encryptType="AES";
        String encryptData="BkxAoj6IOk1T4%2FXdkagZUpziqxBtHwvSVYYLxk8yvUwRNrRaZYwGiy5%2BJxyi8cTWG9IbKAg1%2Bl7I%2Bbe7uiDiEN4AonQNNdQlX%2FfYRdhqxlj8h8xFlo%2BrWSox%2F2TNTsGAikwhMd8RQj7aLhlPvxjY4X3iDwEHFzri0cQ4WesGpfIH%2Bwc%2BAkVzBsdiGNii7PiD14L1uqCD4B1kukSUsGUT1%2BSd2SqVBjNAe9%2Fa48EiAlHpeOHJE6UMiqDvR7pM%2B350";
        String encryptKey="BQhVCoNLVl/GK4BRgxe3masmnlxsy99PVaUrO6B/0gGw/NwiwoY9cjHthjU8PHZ/9QTk9PYFlvI/8LX51J+IK0HVXQqSJ+12i59sXD7VRHf4QsPsoxNZNVArrCtLCIWW3KgiXQohjMyDK1WFLKu0tKZld4Y7JHJ9VYmzpGf0FUs/TLEdIfFkkkWFNIesLUhh+NyYVP6tolgWkUFLzyRWyQxIOAVZu2WkLCL+2Vh+/Az6oXIcLCfnI03gMh6SHIP0bgbxULsH+fqpu7XwXWKZjK2qDYun7+/oznAygcLlYpqCxq+dJJWN9jGkbDye9Me4OVxZpSZGu+eZor+yxurq28q5QCJ82CVnHJFUXS1tWzWj88rsSL+Q7NoLvWpzLmUeQmq7wcTWpkx5GoHN4Gnc9rKmzMblcchFz0atzwaIJEg1qtwIW9b7vjoY73b9DGXSniHVs5j4rgSK1HI3YfTxmsxQoGfqVbrt706alket1M3KU+bG70IkhaFHvhfi/9Sw1idy6F7Fc7HMTrmBkMKUzw==";
        String notifyTime="2008141615";
        String serialNo="20181203203934795384";
        String businessTypeId="01";
        String signType="RSA";
        //parameters.put("mfrchant_id", mfrchant_id);
        parameters.put("encryptType", encryptType);
        parameters.put("encryptData", encryptData);
        parameters.put("encryptKey",encryptKey);
        parameters.put("notifyTime",notifyTime);
        parameters.put("serialNo",serialNo);
        parameters.put("businessTypeId",businessTypeId);
        parameters.put("signType",signType);
        String characterEncoding = "UTF-8";         //指定字符集UTF-8
        String mySign = createSign(characterEncoding,parameters,privateKey);
        System.out.println("我 的签名是："+mySign);
    }

    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters,String key) throws Exception {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbkey = new StringBuffer();
        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if(null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
                sbkey.append(k + "=" + v + "&");
            }
        }
        //System.out.println("字符串:"+sb.toString());
        sbkey=sbkey.append("key="+key);
        System.out.println("字符串:"+sbkey.toString());
//         AES加密
//        String sign = AESUtils.aesEncrypt(sbkey.toString().getBytes(characterEncoding), AESUtils.generateLenString(16).getBytes(characterEncoding), null);
        //MD5加密,结果转换为大写字符
//        String sign = MD5Util.MD5Encode(sbkey.toString(), characterEncoding).toUpperCase();
//        System.out.println("MD5加密值:"+sign);
        // RSA加密
        String sign = RSAUtils.sign(sbkey.toString(), key).toUpperCase();

        System.out.println("AES加密值:"+sign);
        return sb.toString()+"sign="+sign;
    }

}
