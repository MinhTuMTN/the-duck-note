# To learn more about how to use Nix to configure your environment
# see: https://developers.google.com/idx/guides/customize-idx-env
{ pkgs, ... }: {
  # Which nixpkgs channel to use.
  channel = "stable-23.11"; # or "unstable"
  # Use https://search.nixos.org/packages to find packages
  packages = [
    pkgs.zulu17
    pkgs.maven
    pkgs.docker
    pkgs.mysql80
  ];

  services.mysql = {
    enable = true;
    package = pkgs.mysql80;
  };

  services.redis = {
    enable = true;
    port = 6379;
  };

  services.docker = {
    enable = true;
  };

  # Sets environment variables in the workspace
  env = { };
  idx = {
    # Search for the extensions you want on https://open-vsx.org/ and use "publisher.id"
    extensions = [
      "vscjava.vscode-java-pack"
      "rangav.vscode-thunder-client"
      "redhat.java"
      "vmware.vscode-spring-boot"
      "vscjava.vscode-gradle"
      "vscjava.vscode-java-debug"
      "vscjava.vscode-java-dependency"
      "vscjava.vscode-java-test"
      "vscjava.vscode-maven"
      "zhuangtongfa.material-theme"
    ];
    workspace = {
      # Runs when a workspace is first created with this `dev.nix` file
      # onCreate = {
      #   install = "mvn clean install";
      # };
      # Runs when a workspace is (re)started
      # onStart = {
      #   run-rabbitmq-docker = "docker rm rabbitmq && docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management";
      # };
    };
  };
}
