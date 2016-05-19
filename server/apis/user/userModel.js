const bcrypt = require('bcrypt');
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
  },

  nickname: {
    type: String,
    unique: true,
    required: true
  },

  signature: {
    type: String,
    required: true
  },

  gender: {
    type: String,
    required: true
  },

  city: {
    type: String,
    required: true
  },

  age: {
    type: Number,
    required: true
  },

  wechatID: {
    type: String
  },

  plans: [{ type: Schema.Types.ObjectId, ref: 'plan' }],

  posts: [{ type: Schema.Types.ObjectId, ref: 'post' }]
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
    return bcrypt.compareSync(plainTextWord, this.password);
  },

  encryptPassword: function (plainTextWord) {
    if (!plainTextWord) {
      return '';
    }

    const salt = bcrypt.genSaltSync(10);

    return bcrypt.hashSync(plainTextWord, salt);
  }
};

module.exports = mongoose.model('user', UserSchema);
