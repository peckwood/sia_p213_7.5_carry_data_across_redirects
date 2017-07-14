**<u>Just run the project and you will see!</u>**

#### How to carry data across redirects? 2 solutions:

1. ~~Concating parameter/value to URL~~ (not recommended, dangerous)
2. Passing data as path variables and/or query parameters using **URL templates**
3. Sending data in flash attributes

#### URL Templates

- easy and straightforward
- safer than concatination, all unsafe characters in propertie *username* are escaped
- limiting, only good for sending simple values

example:

- `return "redirect:/spitter/" + spitter.getUsername(); ` can be changed to 

  ```java
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(Spitter spitter, Model model) {
  	spitterRepository.save(spitter);
  	model.addAttribute("username", spitter.getUsername());
  	return "redirect:/spitter/{username}";
  }
  //result: /spitter/myUsername
  ```

or

  ```java
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(Spitter spitter, Model model) {
  	spitterRepository.save(spitter);
  	model.addAttribute("username", spitter.getUsername());
    model.addAttribute("spitterId", spitter.getId());
  	return "redirect:/spitter/{username}";
  }
   //result: /spitter/myUsername?spitterId=28
  ```
#### Flash attributes

- Session based
- carry data to the next request and disappear
- can transmit complex data (like object Spittle)

How it works

before:![](http://oojmjfxmz.bkt.clouddn.com/17-7-14/32491731.jpg)

after:![](http://oojmjfxmz.bkt.clouddn.com/17-7-14/70545527.jpg)

example:

```java
@RequestMapping(value="/register", method=POST)
public String processRegistration(
  Spitter spitter, RedirectAttributes model) {
  spitterRepository.save(spitter);
  model.addAttribute("username", spitter.getUsername());
  model.addFlashAttribute("spitter", spitter);
  return "redirect:/spitter/{username}";
}
```

> The images used in this file are from book `Spring in Action 4th edition` by Craig Walls. It is published by Manning Publications. I don't own any copyright. 