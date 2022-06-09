import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { authToAPI } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";

const credentials = {
  email: "",
  password: "",
};

export default function Login() {
  const { dispatch } = useContext(AppContext);

  const [login, setLogin] = useState(credentials);
  const navigate = useNavigate();

  const authentication = () => {
    const url = URL_API;
    const request = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(login),
    };
    authToAPI(url, request, dispatch);
  };

  return (
    <div className="login-form-bg h-100">
      <div className="container h-100">
        <div className="row justify-content-center h-100">
          <div className="col-xl-6">
            <div className="form-input-content">
              <div
                className="card login-form mb-0"
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <div className="card-body pt-5">
                  <a className="text-center" href="TheBeaver">
                    {" "}
                    <h4>Beaver App</h4>
                  </a>

                  <form className="mt-5 mb-5 login-input">
                    <div className="form-group">
                      <input
                        type="email"
                        className="form-control"
                        placeholder="Email"
                        onChange={(e) =>
                          setLogin({ ...login, email: e.target.value })
                        }
                      />
                    </div>
                    <div className="form-group">
                      <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
                        onChange={(e) =>
                          setLogin({ ...login, password: e.target.value })
                        }
                      />
                    </div>
                    <button
                      className="btn login-form__btn submit w-100"
                      type="button"
                      onClick={() => authentication()}
                    >
                      Sign In
                    </button>
                  </form>
                  <p className="mt-5 login-form__footer">
                    {" "}
                    <button
                      className="text-primary"
                      style={{
                        background: "none",
                        backgroundColor: "none",
                        border: "none",
                        color: "#069",
                        textDecoration: "none",
                        cursor: "pointer",
                      }}
                      onClick={() => {
                        navigate("register", { replace: true });
                      }}
                    >
                      Register Account
                    </button>{" "}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
