const asyncHandler = require('express-async-handler')

const Products = require('../models/products')
const multer = require("multer");
const path = require("path");

const listProducts = asyncHandler(async (req, res) => {
    var cat = req.query.cat;
    if(req.query.s){
       var search = req.query.s; 
  Products.find({name:{'$regex': search},cat:cat}, function(err, product) 
  {
     if (product.length == 0)
     {
      res.status(200).json({result:"No result"});
     } else {
      res.status(200).json(product);
     }
  });
        
    }else{
        Products.find({cat:cat}, function(err, product) 
  {
     if (product.length == 0)
     {
      res.status(200).json({result:"No result"});
     } else {
      res.status(200).json(product);
     }
  });

  }
 })

const addProduct = asyncHandler(async (req, res) => {

    NewProduct = await Products.create({
    name: req.body.name,
    price: req.body.price,
    date: req.body.date,
    cat: req.body.cat,
    image: `https://bonplan.clicktechnol.info/images/${req.body.image}`,
    description: req.body.description,
    tel: req.body.tel,
    })  
      res.status(200).json({result:"success",prod:NewProduct});
})



module.exports = {
  listProducts,
  addProduct
}
