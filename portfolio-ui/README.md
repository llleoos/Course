# Portfolio Manager - Frontend UI

Vue3 + Vite frontend for Portfolio Management System.

## Tech Stack

- Vue 3.4
- Vite 5.0
- Vue Router 4
- Pinia (State Management)
- Axios (HTTP Client)
- Element Plus (UI Framework)
- ECharts 5 (Charts)

## Project Structure

```
portfolio-ui/
├── src/
│   ├── api/           # API Services
│   │   └── portfolio.js
│   ├── assets/        # Static Assets
│   ├── components/     # Reusable Components
│   ├── router/         # Vue Router Config
│   │   └── index.js
│   ├── store/          # Pinia Stores
│   │   └── portfolio.js
│   ├── views/          # Page Components
│   │   ├── PortfolioList.vue
│   │   ├── PortfolioAdd.vue
│   │   ├── PortfolioEdit.vue
│   │   └── PortfolioSummary.vue
│   ├── App.vue         # Root Component
│   └── main.js         # Application Entry
├── index.html
├── package.json
└── vite.config.js
```

## Prerequisites

- Node.js 18+
- npm or yarn

## Setup

```bash
# Install dependencies
npm install

# Start development server
npm run dev
```

The application will start on `http://localhost:3000`

## Pages

### Portfolio List (`/portfolio`)
- Display all portfolio assets in a table
- Actions: Add, Edit, Delete
- Show profit/loss with color coding

### Add Asset (`/portfolio/add`)
- Form to create new portfolio asset
- Fields: Ticker, Asset Type, Quantity, Purchase Price, Purchase Date

### Edit Asset (`/portfolio/edit/:id`)
- Form to update existing asset
- Includes current price field

### Portfolio Summary (`/portfolio/summary`)
- Total portfolio value and profit
- Asset allocation pie chart using ECharts

## API Integration

The frontend proxies API requests to the backend:

- Backend must be running on `http://localhost:8080`
- API base URL: `/api`

## Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run preview` - Preview production build
