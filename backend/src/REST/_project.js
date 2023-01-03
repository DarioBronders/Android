const Router = require("@koa/router");
const projectService = require("../service/project");

const getProjects = async (ctx) => {
  ctx.body = await projectService.getAll();
}

const getProjectById = async(ctx) => {
  ctx.body = await projectService.getProjectById(ctx.params.id)
}

const getProjectByCustomerId = async(ctx) => {
  console.log("Projects request received with customer id: " + ctx.params.id);
  ctx.body = await projectService.getByCustomerId(ctx.params.id)
}

module.exports = (app) => {
  const router = new Router({prefix:"/project"});

  router.get("/", getProjects);
  router.get("/:id", getProjectById);
  router.get("/customer/:id", getProjectByCustomerId)

  app.use(router.routes()).use(router.allowedMethods());
}