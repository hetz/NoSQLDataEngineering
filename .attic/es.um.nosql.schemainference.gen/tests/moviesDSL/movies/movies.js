
//File Movie1
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  
// add required for Movie1 entity version
  	genre:	{type:String, required:true},
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
  
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);


//File Movie2
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  
// add required for Movie2 entity version
  	genre:	{type:String, required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
  
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);


//File Movie3
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  
// add required for Movie3 entity version
  	genre:	{type:String, required:true},
	prizes:	{type:Movieprizes3, required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
  
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);


//File Movie4
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  
// add required for Movie4 entity version
  	genre:	{type:String, required:true},
	criticisms:	{type:Moviecriticisms4, required:true},
// Not Common Properties 
	prizes:	Movieprizes3,
  
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);


//File Movie5
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  
// add required for Movie5 entity version
  	genres:	{type:[], required:true},
  	writers:	{type:[], required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
  
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);



//File Director1
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var directorSchema = new mongoose.Schema({
// Common Properties	
  	_id:	{type:String, required:true},
  	name:	{type:String, required:true},
  	type:	{type:String, required:true},
  
// add required for Director1 entity version
  	actor_movies:	{type:[], required:true},
  	directed_movies:	{type:[], required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
  
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);


//File Director2
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});
nC Ags: Moviecriticisms4
var Moviecriticisms4=	{
	color: {type: String ,enum: [green, red, yellow]},
	journalist:	String,
	media:	String,
	url:	String,
    } 
nC Ags: Movieprizes3
var Movieprizes3=	{
	event:	String,
	year:	Number,
    } 
var directorSchema = new mongoose.Schema({
// Common Properties	
  	_id:	{type:String, required:true},
  	name:	{type:String, required:true},
  	type:	{type:String, required:true},
  
// add required for Director2 entity version
  	directed_movies:	{type:[], required:true},
// Not Common Properties 
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
  
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);



