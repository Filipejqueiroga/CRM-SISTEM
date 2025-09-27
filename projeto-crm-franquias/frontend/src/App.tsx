// src/App.tsx
import AppRoutes from './navigation/AppRoutes';

function App() {
  // Poderia ter um layout global aqui (cabeçalho, rodapé, etc.)
  return (
    <div>
      <AppRoutes />
    </div>
  );
}

export default App;