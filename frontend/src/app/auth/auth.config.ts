interface AuthConfig {
  clientID: string;
  domain: string;
  callbackURL: string;
}

export const AUTH_CONFIG: AuthConfig = {
  clientID: '4NuqRd0cFxomtzbNwTcVc2o9kymEKanM',
  domain: 'humanhealth.auth0.com',
  callbackURL: 'http://localhost:4200/#/main'
};
