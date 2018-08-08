package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", ""})
@Slf4j
public class IndexController {

  private final StringRedisTemplate redisTemplate;

  public IndexController(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @GetMapping
  public String index(Model model) {
    log.debug("IndexController IN");
    model.addAttribute("message", "hello world");
    String value = redisTemplate.opsForValue().get("mykey1");
    model.addAttribute("mykey1", value);
    log.debug("IndexController OUT");
    return "index";
  }

}
