package com.jyss.mst.action;

import com.jyss.mst.utils.ValidateCode;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class CodeImgAction
{
  @RequestMapping("/shiro-getCode")
  public void getCode(HttpServletResponse response, HttpSession session)
  {
    ValidateCode vCode = new ValidateCode(100, 30, 4, 50);
    session.removeAttribute("codeImg");
    try {
      vCode.write(response.getOutputStream());
      session.setAttribute("codeImg", ValidateCode.getCode());
      System.out.println(ValidateCode.getCode());
      
      vCode.write(response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
