package tools;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author lk
 * 2018/7/18 10:21
 * @description: 生成登陆的图片验证码
 */
public class DrawPictures {
    /**
     * 生成sessionId 便于服务器端进行存取数据
     */
    private static final String SESSIONID = "SessionPictures";
    /**
     * 画出图片的长
     */
    private final int weight = 95;
    /**
     * 画出图片的宽
     */
    private final int height = 25;
    /**
     * 干扰线的数量
     */
    private final int lineSize = 40;
    /**
     * 图片中字符的数量
     */
    private final int stringSize = 4;
    /**
     * 随机数生成初始化
     */
    private Random random = new Random();
    /**
     * 随机选取字符生成验证码
     */
    private String randString = "ABCDEFGHIJKLMNOPQRSLUVWXYZ";

    /**
     * 随机生成每次画笔的颜色
     *
     * @param fc 最小随机数
     * @param bc 最大随机数
     * @return 返回一个rgb格式的颜色
     */
    private Color getColor(int fc, int bc) {
//        if (fc > 255) {
//            fc = 255;
//        }
//        if (bc > 255) {
//            bc = 255;
//        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制单条干扰线
     *
     * @param graphics 画笔
     */
    private void drowLine(Graphics graphics) {
        int x = random.nextInt(weight);
        int y = random.nextInt(height);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        graphics.setColor(getColor(80, 250));
        graphics.drawLine(x, y, x1, y1);
    }

    /**
     * 生成随机验证码的字体形式
     *
     * @return 返回字体形式
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 在26个大写字母中随机选举一个字母作为验证码
     *
     * @param num 随机数
     * @return 返回一个字符
     */
    private String getRandString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * 从randString中随机选举一个字母，将它画到画布上，同时将每次生成的字符返回
     * @param graphics     画笔
     * @param s 返回生成的随机字符
     * @param i            当前字符位置
     * @return 返回字符
     */
    private String drawCode(Graphics graphics, String s, int i) {
        graphics.setFont(getFont());
        graphics.setColor(new Color(random.nextInt(200), random.nextInt(150), random.nextInt(123)));
        String rand = String.valueOf(getRandString(random.nextInt(randString.length())));
        s += rand;
        graphics.translate(random.nextInt(3), random.nextInt(3));
        graphics.drawString(rand, 13 * i, 16);
        return s;
    }

    /**
     * 图片代码生成，同时通过session发送到页面，和客户端
     * @param request  向session中添加图片
     * @param response 将图片输出到客户端
     */
    public void randCode(HttpServletRequest request, HttpServletResponse response) {
        //创建一个session对象
        HttpSession session = request.getSession();
        //初始化画布大小以及画布的颜色格式rbg
        BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_3BYTE_BGR);
        //初始化画笔
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, weight, height);
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //绘制若干条干扰线
        for (int x = 0; x < lineSize; x++) {
            drowLine(graphics);
        }
        //绘制验证码字母
        String randCode = "";
        for (int x = 0; x < stringSize; x++) {
            randCode = drawCode(graphics, randCode, x);
        }
        //销毁session中指定名字的对应的value，同时将生成的验证码加入session中
        session.removeAttribute(SESSIONID);
        session.setAttribute(SESSIONID, randCode);
        graphics.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
