import { HumanhealthWebPage } from './app.po';

describe('humanhealth-web App', () => {
  let page: HumanhealthWebPage;

  beforeEach(() => {
    page = new HumanhealthWebPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
