function userLogIn(session) {
  with(session) {
    // login
    click(s, xpathsUser.HomePage.myAccountButton)
    click(s, xpathsUser.HomePage.loginButton)
    // enter email and password in inputs
    writeText(s, xpathsUser.LoginPage.emailInput, userEmail)
    writeText(s, xpathsUser.LoginPage.passwordInput, userPassword)
    // click login button
    click(s, xpathsUser.LoginPage.loginButton)
  }
}

function userSearchProduct(session) {
  with(session) {
    // search for product
    writeText(s, xpathsUser.ProductPage.searchInput, product)
    click(s, xpathsUser.ProductPage.searchButton)
  }
}

function userAddToCart(session) {
  with(session) {
    // add product to cart
    click(s, xpathsUser.ProductPage.addToCartButton)
  }
}

function userNavigatesToCheckout(session) {
  with (session) {
    // close success message
    click(s, xpathsUser.ProductPage.successMessageCloseButton)
    // navigate to checkout
    click(s, xpathsUser.ProductPage.itemsInCartButton)
    click(s, xpathsUser.ProductPage.checkoutButton)
  }
}
function adminLogsIn(session) {
  with(session) {
    // login
    writeText(s, xpathsAdmin.LoginPage.usernameInput, adminUserName)
    writeText(s, xpathsAdmin.LoginPage.passwordInput, adminPassword)
    click(s, xpathsAdmin.LoginPage.loginButton)
  }
}

function adminNavigatesToProductsPage(session) {
  with(session) {
    // navigate to products page
    click(s, xpathsAdmin.Dashboard.sidebarButton)
    click(s, xpathsAdmin.Dashboard.catalogButton)
    click(s, xpathsAdmin.Dashboard.productsButton)
  }
}

function adminFiltersProduct(session) {
  with(session) {
    // filter product
    click(s, xpathsAdmin.Dashboard.filterIcon)
    click(s, xpathsAdmin.Dashboard.filterButton)
    writeText(s, xpathsAdmin.Dashboard.productNameInput, product)
    click(s, xpathsAdmin.Dashboard.filterButton)
  }
}

function adminEditsProductsPrice(session) {
  with(session) {
    // edit product
    click(s, xpathsAdmin.Dashboard.editProductButton)
    // navigate to data tab
    click(s, xpathsAdmin.Dashboard.dataOfProductButton)
    // edit price
    writeText(s, xpathsAdmin.ProductPage.priceInput, productPrice)
    // save
    click(s, xpathsAdmin.ProductPage.saveButton)
    // change flag
    isPriceChanged = true
  }
}

