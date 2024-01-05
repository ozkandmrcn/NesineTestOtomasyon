Feature: Login Success Test

  Scenario: Başarısız kullanıcı girişi senaryosu oluşturulması

  @Given Kullanıcı uygulama ana sayfasında
  @When Kullanıcı Giriş Yap butonuna tıklar
  @When Kullanıcı kullanıcı adı ve şifre alanlarını doldurur
  @When Kullanıcı Giriş Yap butonuna tıklar
  @Then Kullanıcı Girişi hatalı popup görür




