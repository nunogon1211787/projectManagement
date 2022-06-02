import React from 'react';

const AppContext = React.createContext(null);
export const {Provider} = AppContext;
export default AppContext;

// export const useAuth = () => {
//     return React.useContext(AppContext);
//   };