// urls
const userURL = 'http://localhost/opencart/'
const adminURL = 'http://localhost/opencart/adminn/index.php?route=common/login'

const USERSESSION='USSES'
const ADMINSESSION='ADSES'

// xpaths
const xpathsUser = {
  HomePage: {
    myAccountButton: "/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/a[@class='dropdown-toggle']/span[@class='d-none d-md-inline']",
    loginButton: "/html/body/nav[@id='top']/div[@class='container']/div[@class='nav float-end']/ul[@class='list-inline']/li[@class='list-inline-item'][2]/div[@class='dropdown']/ul[@class='dropdown-menu dropdown-menu-right show']/li[2]/a[@class='dropdown-item']"
  },
  LoginPage: {
    emailInput: "//*[@id='input-email']",
    passwordInput: "//*[@id='input-password']",
    loginButton: "//*[@id='form-login']/div/button[1]"
  },
  ProductPage: {
    searchInput: "/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/input[@class='form-control form-control-lg']",
    searchButton: "/html/body/header/div[@class='container']/div[@class='row']/div[@class='col-md-5']/div[@id='search']/button[@class='btn btn-light btn-lg']/i[@class='fa-solid fa-magnifying-glass']",
    addToCartButton: "/html/body/main/div[@id='product-search']/div[@class='row']/div[@id='content']/div[@id='product-list']/div[@class='col mb-3'][1]/div[@class='product-thumb']/div[@class='content']/form/div[@class='button-group']/button[1]",
    addedToCartMessage: "/html/body/div[@id='alert']/div[@class='alert alert-success alert-dismissible']",
    successMessageCloseButton: "/html/body/div[@id='alert']/div[@class='alert alert-success alert-dismissible']/button[@class='btn-close']",
    itemsInCartButton: "/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/button[@class='btn btn-lg btn-inverse btn-block dropdown-toggle']",
    nameOfProductInCart: "/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/table[@class='table table-striped mb-2']/tbody/tr/td[@class='text-start']/a",
    checkoutButton: "/html/body/header/div[@class='container']/div[@class='row']/div[@id='header-cart']/div[@class='dropdown d-grid']/ul[@class='dropdown-menu dropdown-menu-end p-2 show']/li/div/p[@class='text-end']/a[2]/strong"
  },
  TearDownObj: {
    cartInfo : "//*[@id='header-cart']/div[1]/button[1]",
    removeFromCartButton: "//*[@id='header-cart']/div/ul/li/table/tbody/tr/td/form/button/i[1]"
  }
}

const xpathsAdmin = {
  LoginPage: {
    usernameInput: "//*[@id='input-username']",
    passwordInput: "//*[@id='input-password']",
    loginButton: "/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='row justify-content-sm-center']/div[@class='col-sm-10 col-md-8 col-lg-5']/div[@class='card']/div[@class='card-body']/form[@id='form-login']/div[@class='text-end']/button[@class='btn btn-primary']"
  },
  Dashboard: {
    sidebarButton: "/html/body/div[@id='container']/header[@id='header']/div[@class='container-fluid']/button[@id='button-menu']/i[@class='fa-solid fa-bars']",
    catalogButton: "/html/body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-catalog']/a[@class='parent collapsed']",
    productsButton: "/html/body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-catalog']/ul[@id='collapse-1']/li[2]/a",
    filterButton: "//*[@id='button-filter']",
    productNameInput: "//*[@id='input-name']",
    editProductButton: "/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='row']/div[@class='col col-lg-9 col-md-12']/div[@class='card']/div[@id='product']/form[@id='form-product']/div[@class='table-responsive']/table[@class='table table-bordered table-hover']/tbody/tr/td[@class='text-end'][3]/div[@class='btn-group']/a[@class='btn btn-primary']",
    dataOfProductButton: "/html/body/div[@id='container']/div[@id='content']/div[@class='container-fluid']/div[@class='card']/div[@class='card-body']/form[@id='form-product']/ul[@class='nav nav-tabs']/li[@class='nav-item'][2]/a[@class='nav-link']"
  },
  ProductPage: {
    priceInput: "//*[@id='input-price']",
    saveButton: "/html/body/div[@id='container']/div[@id='content']/div[@class='page-header']/div[@class='container-fluid']/div[@class='float-end']/button[@class='btn btn-primary']",
    successMessage: "/html/body/div[@id='container']/div[@id='alert']/div[@class='alert alert-success alert-dismissible']"
  }
}

const scrolling = {
  down: "window.scrollTo(0, document.body.scrollHeight)",
  up: "window.scrollTo(0, 0)"
}

// user constants
const userEmail = 'lioraftabi@gmail.com'
const userPassword = '12345678'
const product = 'MacBook'

// admin constant
const adminUserName = 'admin'
const adminPassword = '1234'
const productPrice = '6000'