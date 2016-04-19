const bycrypt = require('bycrypt');
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const UserSchema = new Schema({
  username: {
    type: String,
    unique: true,
    required: true
  },

  password: {
    type: String,
    required: true
  }
});

UserSchema.pre('save', function (next) {
  if (!this.isModified('password')) {
    return next();
  } else {
    this.password = this.encryptPassword(this.password);
    next();
  }
});

UserSchema.methods = {
  toJson: function () {
    // Convert mongoose object to plain JavaScript object
    const obj = this.toObject();
    delete obj.password;

    return obj;
  },

  authenticate: function (plainTextWord) {
    return bycrypt.compareSync(plainTextWord, this.password);
  },

  encryptPassword: function (plainTextWord) {
    if (!plainTextWord) {
      return '';
    }

    const salt = bycrypt.getSaltSync(10);

    return bycrypt.hashSync(plainTextWord, salt);
  }
};

module.exports = mongoose.model('user', UserSchema);
