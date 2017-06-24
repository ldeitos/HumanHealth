import {CustomConfig} from 'ng2-ui-auth';
/**
 * Created by Ron on 03/10/2016.
 */
const GOOGLE_CLIENT_ID = '650030074470-8p4trg5bt0mchf0sok3edgjvhndo993t.apps.googleusercontent.com';
const FACEBOOK_ID = '183661612166704';

export class AuthConfig extends CustomConfig {
    defaultHeaders = {'Content-Type': 'application/json'};
    providers = {google: { clientId: GOOGLE_CLIENT_ID },
                 facebook: {clientId: FACEBOOK_ID}
                };
}