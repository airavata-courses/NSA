import React from "react";
import { createContext, useReducer, useContext } from 'react';


const GlobalStateContext = createContext();
const SET_USER = 'SET_USER';
const initialState = {
  user: {
    userName: null,
  }
};

const globalStateReducer = (state, action) => {
  switch (action.type) {
    case SET_USER:
      return {
        ...state,
        user: { ...action.payload },
      };

    default:
      return state;
  }
};

export const GlobalStateProvider = ({ children }) => {
  const [state, dispatch] = useReducer(
    globalStateReducer,
    initialState
  );

  return (
    <GlobalStateContext.Provider value={[state, dispatch]}>
      {children}
    </GlobalStateContext.Provider>
  );
};

const useGlobalState = () => {
  const [state, dispatch] = useContext(GlobalStateContext);

  const setUser = userName => {
    dispatch({
      type: SET_USER,
      payload: {
        userName,
      }
    });
  };

  return {
    setUser,
    user: { ...state.user },
  };
};

export default useGlobalState;