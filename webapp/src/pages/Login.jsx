export default function Login() {
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
                        class="form-control"
                        placeholder="Email"
                      />
                    </div>
                    <div className="form-group">
                      <input
                        type="password"
                        class="form-control"
                        placeholder="Password"
                      />
                    </div>
                    <button className="btn login-form__btn submit w-100">
                      Sign In
                    </button>
                  </form>
                  <p className="mt-5 login-form__footer">
                    {" "}
                    <a href="/users" className="text-primary">
                      Register Account
                    </a>{" "}
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
