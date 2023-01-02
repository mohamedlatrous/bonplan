const mongoose = require('mongoose')

const studentSchema = mongoose.Schema(
  {
    name: {
      type: String,
    },
    price: {
      type: String,
    },
    date: {
      type: String,
    },
    cat: {
      type: String,
    },    
    image: {
      type: String,
    },
    description: {
      type: String,
    },
    tel: {
      type: String,
    },


  },
  {
    timestamps: true,
  }
)

module.exports = mongoose.model('products', studentSchema)
