import MainRoute from "./routes/MainRoutes";
import AppProvider from "./context/AppProvider";
import Sidebar from "./components/Sidebar";

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
