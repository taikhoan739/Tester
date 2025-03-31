import time
from unittest import expectedFailure

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

service = Service(executable_path="chromedriver.exe")
driver = webdriver.Chrome(service=service)
URL ="https://dhthanhit.pythonanywhere.com/"
kw= input("Nhap kw=")

def search(url,kw):
    driver.get(url)
    input = driver.find_element(By.CSS_SELECTOR,"input[name=keyword]")
    input.send_keys(kw)
    button_submit =  driver.find_element(By.CSS_SELECTOR,"button[type=submit]")
    button_submit.click()
    # driver.implicitly_wait(100000)
    time.sleep(100000)
    driver.quit()

def get_products(url):
    driver.get(url)
    print(driver.title)
    wait = WebDriverWait(driver, 10)
    # test =  wait.until(EC.presence_of_element_located(By.ID,"Id-element"))
    categories = driver.find_elements(By.CLASS_NAME, "nav-item")
    urls = []
    for c in categories:
        link = c.find_element(By.TAG_NAME, "a")
        href = link.get_attribute("href")
        # name = link.text
        if ("category_id" in href):
            urls.append(href)
    for url in urls:
        print("DANH MUC--------------")
        print(url)
        print("SAN PHAM----------")
        driver.get(url)
        products = driver.find_elements(By.CLASS_NAME, "card")
        for p in products:
            title = p.find_element(By.CLASS_NAME, "card-title")
            price = p.find_element(By.CLASS_NAME, "card-text")
            img = p.find_element(By.TAG_NAME, "img")
            print(title.text + '\n' + price.text + '\n' + img.get_attribute("src"))
        print("\n")

    time.sleep(200000)
    driver.quit()
if __name__ == "__main__":
    # search(URL,"galaxy")
    get_products(URL)