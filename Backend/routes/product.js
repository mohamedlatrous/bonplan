const express = require('express')
const router = express.Router()
const {
  listProducts,
  addProduct,
}= require('../controllers/productController')

router.route('/list').get( listProducts)
router.route('/add').post( addProduct)

module.exports = router
