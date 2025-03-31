import time
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

service = Service(executable_path="chromedriver.exe")
driver = webdriver.Chrome(service=service)
URL ="https://tiki.vn/ky-luat-ban-than-p190238356.html?spid=190238357/"

if __name__ == "__main__":
    driver.get(URL)
    print(driver.title)
    titles = []
    driver.execute_script("window.scrollTo(0,2000);")
    pages = WebDriverWait(driver, 10).until(
        EC.presence_of_all_elements_located((By.CSS_SELECTOR, ".customer-reviews__pagination li a"))
    )
    for page in pages:
        print(page.text)
        if(page.text):
            page.click()
            time.sleep(1)
            contents = WebDriverWait(driver, 20).until(
                EC.presence_of_all_elements_located((By.CLASS_NAME, "review-comment__content"))
            )
            for c in contents:
                print(c.text)
    driver.quit()
