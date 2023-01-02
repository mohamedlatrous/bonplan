const express = require('express')
const router = express.Router()
const {
  getUser,
  setUser,
}= require('../controllers/userController')

router.route('/login').post( getUser)
router.route('/signup').post( setUser)

module.exports = router
