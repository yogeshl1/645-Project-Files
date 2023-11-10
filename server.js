const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const app = express();
const PORT = 3000;

// MongoDB connection
mongoose.connect('mongodb://localhost:27017/restaurantdb', {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

// Restaurant schema and model
const restaurantSchema = new mongoose.Schema({
  name: String,
  menu: String,
  isPopular: Boolean
});

const Restaurant = mongoose.model('Restaurant', restaurantSchema);

app.use(bodyParser.json());

// Route to fetch restaurants
app.get('/api/restaurants', async (req, res) => {
  try {
    const restaurants = await Restaurant.find();
    res.json(restaurants);
  } catch (error) {
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

// Route to fetch menu items by restaurant ID (assuming you have a Menu model)
app.get('/api/menu/:restaurantId', async (req, res) => {
  const restaurantId = req.params.restaurantId;
  // Implement logic to fetch menu items based on restaurantId from your Menu model
  // Example: const menuItems = await Menu.find({ restaurantId: restaurantId });
  // res.json(menuItems);
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
