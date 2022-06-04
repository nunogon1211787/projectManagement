import MainRoute from "./routes/MainRoutes";
import AppProvider from "./context/AppProvider";

function App() {
  return (
    <>
      <AppProvider>
        <MainRoute />
      </AppProvider>
    </>
  );
}

export default App;
