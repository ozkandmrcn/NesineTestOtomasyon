Feature: Login Test

  Scenario: Başarılı kullanıcı girişi senaryosu oluşturulması

  @Given Kullanıcı uygulama ana sayfasında
  @When Kullanıcı Giriş Yap butonuna tıklar
  @When Kullanıcı kullanıcı adı ve şifre alanlarını doldurur
  @When Kullanıcı Giriş Yap butonuna tıklar
  @Then Kullanıcı Anasayfaya yönlendirilir
  @When Anasayfa üzerinden hesabım sayfasına gidilir
  @When Kullanıcı çıkış yap butonuna tıklar
  @Then Kullanıcı çıkış yaptığını görür



