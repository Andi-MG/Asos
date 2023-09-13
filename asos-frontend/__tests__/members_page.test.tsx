import {render, screen } from "@testing-library/react";
import Members from "@/pages/members/index";
import {rest} from 'msw'
import {setupServer} from 'msw/node'

// declare which API requests to mock
const server = setupServer(
  // capture "GET /greeting" requests
  rest.get('/members', (req, res, ctx) => {
    // respond using a mocked JSON body
    return res(ctx.json({greeting: 'hello there'}))
  }),
)

// establish API mocking before all tests
beforeAll(() => server.listen())
// reset any request handlers that are declared as a part of our tests
// (i.e. for testing one-time error scenarios)
afterEach(() => server.resetHandlers())
// clean up once the tests are done
afterAll(() => server.close())


describe("Members", () => {
  it("renders a list", () => {
    //render(<Members />)
    const heading = screen.getByRole("heading", {
      name: /association management/i,
    });

    expect(heading).toBeInTheDocument();
  });
});
